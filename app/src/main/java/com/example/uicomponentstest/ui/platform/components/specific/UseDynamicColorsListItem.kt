package com.example.uicomponentstest.ui.platform.components.specific

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.runtime.Composable
import com.example.uicomponentstest.ui.feature.settingspage.SettingsUiStateLoaded
import com.example.uicomponentstest.ui.platform.components.reusable.ListItemWithSwitch

@Composable
fun UseDynamicColorsListItem(
    settingsUiStateLoaded: SettingsUiStateLoaded,
    onChangeDynamicColorPreference: (useDynamicColor: Boolean) -> Unit,
) {

    var checked = settingsUiStateLoaded.useDynamicColor

    ListItemWithSwitch(
        text = "Use Material 3 dynamic colors",
        enabled = checked,
        onCheckedChange = {
            checked = it
            onChangeDynamicColorPreference(it)
        },
        enabledIcon = Icons.Rounded.Check,
        disabledIcon = null,
    )
}