package com.example.uicomponentstest.data.model.settings

import com.example.uicomponentstest.ui.platform.components.reusable.ISelectableOption

/**
 * An enumeration representing different theme options for an application.
 *
 * @property displayName The display name of the theme option. This is used to present the option in a user interface.
 */
enum class ThemeConfig(override val displayName: String) : ISelectableOption {
    LIGHT(displayName = "Light"),
    DARK(displayName = "Dark"),
    SYSTEM_DEFAULT(displayName = "System default")
}