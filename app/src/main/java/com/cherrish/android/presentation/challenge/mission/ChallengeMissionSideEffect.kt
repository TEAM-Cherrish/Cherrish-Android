package com.cherrish.android.presentation.challenge.mission

sealed interface ChallengeMissionSideEffect {
    data object NavigateToChallengeMissionProgress : ChallengeMissionSideEffect
}
