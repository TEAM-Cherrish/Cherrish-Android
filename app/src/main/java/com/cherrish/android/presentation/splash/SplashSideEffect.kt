package com.cherrish.android.presentation.splash

sealed interface SplashSideEffect {
    data object NavigateToOnboarding : SplashSideEffect
    data object NavigateToHome : SplashSideEffect
}
