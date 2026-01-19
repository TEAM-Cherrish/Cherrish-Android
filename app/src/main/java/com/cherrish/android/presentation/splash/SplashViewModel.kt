package com.cherrish.android.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cherrish.android.core.local.TokenManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val tokenManager: TokenManager
) : ViewModel() {
    private val _sideEffect = MutableSharedFlow<SplashSideEffect>()
    val sideEffect: SharedFlow<SplashSideEffect> = _sideEffect.asSharedFlow()

    fun isAutoLoginCheck() {
        viewModelScope.launch {
            val id = tokenManager.getId()
            _sideEffect.emit(
                if (id != null) {
                    SplashSideEffect.NavigateToHome
                } else {
                    SplashSideEffect.NavigateToOnboarding
                }
            )
        }
    }
}
