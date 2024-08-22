package com.example.uicomponentstest.ui.platform.components.reusable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role

@Composable
fun ListItemDemo() {
    Column {
        ListItem(headlineContent = { Text("headlineContent") },
            supportingContent = { Text("supportingContent") },
            trailingContent = { Text("trailing") },
            overlineContent = { Text("OVERLINE") },
            leadingContent = {
                Icon(
                    Icons.Filled.Favorite,
                    contentDescription = "Localized description",
                )
            })
    }
}

/**
 * Composable function that displays a list item with a text label and a switch.
 *
 * This function provides a clickable list item that contains a text label on the left
 * and a switch on the right. The state of the switch is determined by the `enabled` parameter,
 * and it can be toggled by clicking on the list item or the switch itself.
 *
 * @param text The text to be displayed as the label for the [ListItem].
 * @param enabled A boolean value indicating the initial state of the [Switch].
 * @param onCheckedChange A callback function that is invoked when the switch's state changes.
 *                        The function should contain the logic to handle the state change.
 * @param enabledIcon An optional Icon which is shown inside the switch when it is checked.
 * @param disabledIcon A optional Icon which is shown inside the switch when it is not checked.
 */
@Composable
fun ListItemWithSwitch(
    text: String,
    enabled: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    enabledIcon: ImageVector?,
    disabledIcon: ImageVector?,
) {
    var checked = enabled
    fun switch() {
        checked = !checked
        onCheckedChange(checked)
    }
    ListItem(modifier = Modifier.clickable(role = Role.Switch, onClick = {
        switch()
    }), headlineContent = { Text(text = text) }, trailingContent = {
        Switch(checked = checked, onCheckedChange = {
            switch()
        }, thumbContent = if (checked) {
            {
                if (enabledIcon != null) {
                    Icon(
                        imageVector = enabledIcon,
                        contentDescription = null,
                        modifier = Modifier.size(SwitchDefaults.IconSize)
                    )
                }
            }
        } else if (disabledIcon != null) {
            {
                Icon(
                    imageVector = disabledIcon,
                    contentDescription = null,
                    modifier = Modifier.size(SwitchDefaults.IconSize)
                )
            }
        } else {
            null
        })
    })
}