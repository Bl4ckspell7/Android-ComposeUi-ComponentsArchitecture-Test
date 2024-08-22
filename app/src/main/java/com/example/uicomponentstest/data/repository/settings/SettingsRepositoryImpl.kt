package com.example.uicomponentstest.data.repository.settings

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.uicomponentstest.data.model.settings.ThemeConfig
import com.example.uicomponentstest.data.model.settings.UserSettings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(private val dataStore: DataStore<Preferences>) :
    SettingsRepository {

    /**
     * Data Store Keys
     */
    private companion object {
        private val THEME_KEY = stringPreferencesKey("theme")
        private val DYNAMIC_COLOR_KEY = booleanPreferencesKey("dynamic_color")
    }

    private val themeConfigFlow: Flow<ThemeConfig> = dataStore.data.map { preferences ->
        preferences[THEME_KEY]?.let { t -> ThemeConfig.valueOf(t) } ?: ThemeConfig.SYSTEM_DEFAULT
    }

    private val dynamicColorFlow: Flow<Boolean> =
        dataStore.data.map { preferences -> preferences[DYNAMIC_COLOR_KEY] ?: true }

    /**
     * combined Flow for retrieving all settings from the repository
     */
    override val settings: Flow<UserSettings> = combine(
        themeConfigFlow, dynamicColorFlow
    ) { themeConfig, useDynamicColors ->
        UserSettings(themeConfig, useDynamicColors)
    }.catch {
        emit(UserSettings(ThemeConfig.SYSTEM_DEFAULT, true))
    }

    override suspend fun saveThemeConfig(themeConfig: ThemeConfig) {
        dataStore.edit {
            it[THEME_KEY] = themeConfig.name
        }
    }

    override suspend fun saveDynamicColor(useDynamicColor: Boolean) {
        dataStore.edit {
            it[DYNAMIC_COLOR_KEY] = useDynamicColor
        }
    }
}