package com.cherrish.android.presentation.challenge.loading

import androidx.compose.runtime.Immutable
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Immutable
data class ChallengeLoadingUiState(
    val routines: ImmutableList<String> = persistentListOf()
)

sealed interface ChallengeLoadingSideEffect {
    data class NavigateToChallengeMission(val routineId: Int, val routines: List<String>) :
        ChallengeLoadingSideEffect
}
