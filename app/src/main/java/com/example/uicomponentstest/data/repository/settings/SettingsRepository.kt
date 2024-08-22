package com.example.uicomponentstest.data.repository.settings

import com.example.uicomponentstest.data.model.settings.ThemeConfig
import com.example.uicomponentstest.data.model.settings.UserSettings
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    val settings: Flow<UserSettings>
    suspend fun saveThemeConfig(themeConfig: ThemeConfig)
    suspend fun saveDynamicColor(useDynamicColor: Boolean)
}