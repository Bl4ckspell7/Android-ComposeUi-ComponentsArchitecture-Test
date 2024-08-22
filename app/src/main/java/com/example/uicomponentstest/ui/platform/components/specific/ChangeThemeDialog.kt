package com.example.uicomponentstest.ui.platform.components.specific

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.example.uicomponentstest.R
import com.example.uicomponentstest.data.model.settings.ThemeConfig
import com.example.uicomponentstest.ui.feature.settingspage.SettingsUiStateLoaded
import com.example.uicomponentstest.ui.feature.settingspage.SettingsViewModel
import com.example.uicomponentstest.ui.platform.components.reusable.DialogPopup
import com.example.uicomponentstest.ui.platform.components.reusable.RadioButtonsGroup

/**
 * Composable function that displays a dialog for changing the application's theme.
 *
 * This function presents a button to open the dialog, a list of available themes,
 * and updates the selected theme when confirmed by the user.
 *
 * @param settingsUiStateLoaded The [SettingsUiStateLoaded] object containing the current theme configuration.
 * @param onChangeThemeConfig A callback function to update the selected theme in the [SettingsViewModel].
 */
@Composable
fun ChangeThemeDialog(
    settingsUiStateLoaded: SettingsUiStateLoaded,
    onChangeThemeConfig: (themeConfig: ThemeConfig) -> Unit,
) {
    val availableThemeConfigs = ThemeConfig.entries
    var selectedTheme = settingsUiStateLoaded.themeConfig

    var openAlertDialog by remember { mutableStateOf(false) }

    /**
     * Button which triggers the dialog to open
     */
    Button(onClick = { openAlertDialog = true }) {
        Text(text = stringResource(R.string.change_theme))
    }

    /**
     * Check if the alert dialog should be open
     */
    if (openAlertDialog) {
        DialogPopup(
            dialogTitle = stringResource(R.string.change_theme),
            dialogContent = {
                RadioButtonsGroup(selectableOptions = availableThemeConfigs,
                    preselectedOption = selectedTheme,
                    trigger = {
                        selectedTheme = it
                    })
            },
            onDismissRequest = { openAlertDialog = false },
            onConfirmation = {
                openAlertDialog = false
                onChangeThemeConfig(selectedTheme)
            },
        )
    }
}