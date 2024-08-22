package com.example.uicomponentstest

import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.uicomponentstest.data.model.settings.ThemeConfig
import com.example.uicomponentstest.ui.feature.importproductpage.ImportProductPage
import com.example.uicomponentstest.ui.feature.importproductpage.ImportProductPageScreen
import com.example.uicomponentstest.ui.feature.productpage.ProductPage
import com.example.uicomponentstest.ui.feature.productpage.ProductPageScreen
import com.example.uicomponentstest.ui.feature.settingspage.SettingsPage
import com.example.uicomponentstest.ui.feature.settingspage.SettingsPageScreen
import com.example.uicomponentstest.ui.feature.testpage.TestPage
import com.example.uicomponentstest.ui.feature.testpage.TestPageScreen
import com.example.uicomponentstest.ui.platform.theme.UIComponentsTestTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var uiState: MainActivityUiState by mutableStateOf(MainActivityUiState.Loading)

        // Update the uiState
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.onEach { uiState = it }.collect()
            }
        }

        enableEdgeToEdge()
        setContent {
            val isDarkThemeEnabled = shouldUseDarkTheme(uiState)

            /**
             * fix status bar not readable (not changing dark/light theme)
             */
            enableEdgeToEdge(
                statusBarStyle = SystemBarStyle.auto(
                    android.graphics.Color.TRANSPARENT,
                    android.graphics.Color.TRANSPARENT,
                ) { isDarkThemeEnabled },
                navigationBarStyle = SystemBarStyle.auto(
                    lightScrim,
                    darkScrim,
                ) { isDarkThemeEnabled },
            )

            val context = LocalContext.current

            if (context is Activity) {
                setPortraitMode(context)
            }

            UIComponentsTestTheme(
                isDarkThemeEnabled, shouldEnableDynamicTheming(uiState = uiState)
            ) {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = TestPage) {
                    composable<TestPage> {
                        TestPageScreen(
                            navController = navController,
                        )
                    }
                    composable<ProductPage> {
                        ProductPageScreen(
                            navController = navController,
                        )
                    }
                    composable<SettingsPage> {
                        SettingsPageScreen(navController = navController)
                    }
                    composable<ImportProductPage> {
                        ImportProductPageScreen(navController = navController)
                    }
                }
            }
        }
    }

    /**
     * Sets the specified activity to portrait mode.
     *
     * @param activity The [Activity] instance whose orientation will be set to portrait mode.
     */
    @SuppressLint("SourceLockedOrientationActivity")
    private fun setPortraitMode(activity: Activity) {
        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }


    /**
     * Returns `true` if the dynamic color is enabled, as a function of the [uiState].
     */
    @Composable
    private fun shouldEnableDynamicTheming(
        uiState: MainActivityUiState,
    ): Boolean = when (uiState) {
        MainActivityUiState.Loading -> true
        is MainActivityUiState.Success -> uiState.userSettings.useDynamicColors
    }

    /**
     * Returns `true` if dark theme should be used, as a function of the [uiState] and the
     * current system context.
     */
    @Composable
    private fun shouldUseDarkTheme(
        uiState: MainActivityUiState,
    ): Boolean = when (uiState) {
        MainActivityUiState.Loading -> isSystemInDarkTheme()
        is MainActivityUiState.Success -> when (uiState.userSettings.themeConfig) {
            ThemeConfig.SYSTEM_DEFAULT -> isSystemInDarkTheme()
            ThemeConfig.LIGHT -> false
            ThemeConfig.DARK -> true
        }
    }

    /**
     * The default light scrim, as defined by androidx and the platform:
     * https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:activity/activity/src/main/java/androidx/activity/EdgeToEdge.kt;l=35-38;drc=27e7d52e8604a080133e8b842db10c89b4482598
     */
    private val lightScrim = android.graphics.Color.argb(0xe6, 0xFF, 0xFF, 0xFF)

    /**
     * The default dark scrim, as defined by androidx and the platform:
     * https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:activity/activity/src/main/java/androidx/activity/EdgeToEdge.kt;l=40-44;drc=27e7d52e8604a080133e8b842db10c89b4482598
     */
    private val darkScrim = android.graphics.Color.argb(0x80, 0x1b, 0x1b, 0x1b)
}