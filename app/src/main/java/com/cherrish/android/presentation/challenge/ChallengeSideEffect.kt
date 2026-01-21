package com.cherrish.android.presentation.challenge

sealed interface ChallengeSideEffect {
    data class NavigateToChallengeLoading(
        val routineId: Int
    ) : ChallengeSideEffect
}
