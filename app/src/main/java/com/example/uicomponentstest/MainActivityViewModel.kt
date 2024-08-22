package com.example.uicomponentstest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uicomponentstest.data.model.settings.UserSettings
import com.example.uicomponentstest.data.repository.settings.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(settingsRepository: SettingsRepository) :
    ViewModel() {
    val uiState: StateFlow<MainActivityUiState> = settingsRepository.settings.map {
        MainActivityUiState.Success(it)
    }.stateIn(
        scope = viewModelScope,
        initialValue = MainActivityUiState.Loading,
        started = SharingStarted.WhileSubscribed(),
    )
}

sealed interface MainActivityUiState {
    data object Loading : MainActivityUiState
    data class Success(val userSettings: UserSettings) : MainActivityUiState
}