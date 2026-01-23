package com.cherrish.android.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {
    private val _sideEffect = MutableSharedFlow<SplashSideEffect>()
    val sideEffect: SharedFlow<SplashSideEffect> = _sideEffect.asSharedFlow()

    fun navigateToOnboarding() {
        viewModelScope.launch {
            _sideEffect.emit(
                SplashSideEffect.NavigateToOnboarding
            )
        }
    }
}
