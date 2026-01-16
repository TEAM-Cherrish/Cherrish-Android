package com.cherrish.android.presentation.challenge.routine

import androidx.compose.runtime.Immutable

@Immutable
data class ChallengeRoutineUiState(
    val routine: String = "",

    val isSelected: Boolean = false
) {
    companion object {
        val FakeRoutine =
            ChallengeRoutineUiState(
                routine = "피부 컨디션",
                isSelected = false
            )
    }
}
