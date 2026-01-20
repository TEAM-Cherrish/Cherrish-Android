package com.cherrish.android.presentation.onboarding

sealed interface OnboardingSideEffect {
    data object NavigateToOnboardingInformation : OnboardingSideEffect
}
