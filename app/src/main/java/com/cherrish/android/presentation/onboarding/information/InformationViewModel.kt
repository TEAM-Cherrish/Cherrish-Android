package com.cherrish.android.presentation.onboarding.information

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cherrish.android.core.common.extension.onLogFailure
import com.cherrish.android.core.local.TokenManager
import com.cherrish.android.data.model.OnboardingProfileRequestModel
import com.cherrish.android.data.repository.OnboardingProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InformationViewModel @Inject constructor(
    private val onboardingProfileRepository: OnboardingProfileRepository,
    private val tokenManager: TokenManager
) : ViewModel() {
    private val _uiState = MutableStateFlow(InformationUiState())
    val uiState: StateFlow<InformationUiState> = _uiState.asStateFlow()

    private val _sideEffect = MutableSharedFlow<InformationSideEffect>()
    val sideEffect: SharedFlow<InformationSideEffect> = _sideEffect.asSharedFlow()

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
        viewModelScope.launch {
            onboardingProfileRepository.postOnboardingProfile(
                request = OnboardingProfileRequestModel(
                    name = uiState.value.username,
                    age = uiState.value.age.toInt()
                )
            ).onSuccess { response ->
                tokenManager.saveId(response.id)
                _sideEffect.emit(InformationSideEffect.NavigateToHome)
            }.onLogFailure {}
        }
    }
}
