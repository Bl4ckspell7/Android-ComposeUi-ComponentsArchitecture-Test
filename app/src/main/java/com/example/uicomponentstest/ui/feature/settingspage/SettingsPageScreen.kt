package com.example.uicomponentstest.ui.feature.settingspage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.uicomponentstest.data.model.settings.ThemeConfig
import com.example.uicomponentstest.ui.feature.testpage.TestPage
import com.example.uicomponentstest.ui.platform.components.specific.ChangeThemeDialog
import com.example.uicomponentstest.ui.platform.components.specific.UseDynamicColorsListItem

@Composable
fun SettingsPageScreen(
    navController: NavController,
    settingsViewModel: SettingsViewModel = hiltViewModel(),
) {
    val settingsUiState by settingsViewModel.settingsUiState.collectAsStateWithLifecycle()
    LoadingScreen(
        settingsUiState = settingsUiState,
        onChangeThemeConfig = settingsViewModel::setTheme,
        onChangeDynamicColorPreference = settingsViewModel::setDynamicColor,
        navController = navController,
    )
}

@Composable
private fun LoadingScreen(
    settingsUiState: SettingsUiState,
    onChangeThemeConfig: (themeConfig: ThemeConfig) -> Unit,
    onChangeDynamicColorPreference: (useDynamicColor: Boolean) -> Unit,
    navController: NavController,
) {
    when (settingsUiState) {
        SettingsUiState.Loading -> {
            Text(text = "Loading...")
        }

        is SettingsUiState.Success -> {
            SuccessScreen(
                settingsUiStateLoaded = settingsUiState.loadedSettings,
                onChangeThemeConfig = onChangeThemeConfig,
                onChangeDynamicColorPreference = onChangeDynamicColorPreference,
                navController = navController,
            )
        }
    }
}

@Composable
private fun SuccessScreen(
    settingsUiStateLoaded: SettingsUiStateLoaded,
    onChangeThemeConfig: (themeConfig: ThemeConfig) -> Unit,
    onChangeDynamicColorPreference: (useDynamicColor: Boolean) -> Unit,
    navController: NavController,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            HorizontalDivider()
            UseDynamicColorsListItem(settingsUiStateLoaded, onChangeDynamicColorPreference)
            HorizontalDivider()
            ChangeThemeDialog(
                settingsUiStateLoaded = settingsUiStateLoaded, onChangeThemeConfig
            )
            HorizontalDivider()
            Button(onClick = { navController.navigate(TestPage) }) {
                Text(text = "Go back")
            }
            HorizontalDivider()
        }
    }
}

