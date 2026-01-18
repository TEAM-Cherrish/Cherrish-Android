package com.cherrish.android.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

@HiltViewModel
class OnboardingViewModel @Inject constructor() : ViewModel() {

    private val _sideEffect = MutableSharedFlow<OnboardingSideEffect>()
    val sideEffect: SharedFlow<OnboardingSideEffect> = _sideEffect.asSharedFlow()

    fun onClick() {
        viewModelScope.launch {
            _sideEffect.emit(
                OnboardingSideEffect.NavigateToOnboardingInformation
            )
        }
    }
}
