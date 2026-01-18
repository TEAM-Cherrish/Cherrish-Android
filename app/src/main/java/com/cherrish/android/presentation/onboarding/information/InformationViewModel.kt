package com.cherrish.android.presentation.onboarding.information

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class InformationViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(InformationUiState())
    val uiState: StateFlow<InformationUiState> = _uiState.asStateFlow()

    fun onNameChanged(name: String) {
        _uiState.update {
            it.copy(
                username = name
            )
        }
    }

    fun onAgeChanged(input: String) {
        val filtered = input.filter { it.isDigit() }
        _uiState.update { it.copy(age = filtered) }
    }

    fun onNextClicked() {
        // TODO: 다음 화면으로 이동
    }
}
