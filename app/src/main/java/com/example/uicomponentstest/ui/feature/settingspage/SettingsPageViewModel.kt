package com.example.uicomponentstest.ui.feature.settingspage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uicomponentstest.data.model.settings.ThemeConfig
import com.example.uicomponentstest.data.repository.settings.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(private val settingsRepository: SettingsRepository) :
    ViewModel() {
    val settingsUiState: StateFlow<SettingsUiState> = settingsRepository.settings.map {
        SettingsUiState.Success(
            loadedSettings = SettingsUiStateLoaded(
                it.themeConfig, it.useDynamicColors
            )
        )
    }.stateIn(
        viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = SettingsUiState.Loading,
    )

    /**
     * Updates the application's theme.
     *
     * @param themeConfig The new theme to be applied.
     */
    fun setTheme(themeConfig: ThemeConfig) {
        viewModelScope.launch {
            settingsRepository.saveThemeConfig(themeConfig)
        }
    }

    /**
     * Sets whether dynamic colors should be enabled or disabled.
     *
     * @param isDynamicColor A boolean indicating if dynamic color should be enabled.
     */
    fun setDynamicColor(isDynamicColor: Boolean) {
        viewModelScope.launch {
            settingsRepository.saveDynamicColor(isDynamicColor)
        }
    }
}

/**
 * Represents the settings which the user can edit within the app.
 */
data class SettingsUiStateLoaded(
    val themeConfig: ThemeConfig,
    val useDynamicColor: Boolean,
)

sealed interface SettingsUiState {
    data object Loading : SettingsUiState
    data class Success(val loadedSettings: SettingsUiStateLoaded) : SettingsUiState
}
