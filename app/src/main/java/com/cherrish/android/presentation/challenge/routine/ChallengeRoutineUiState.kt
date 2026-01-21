//ChallengeRoutineUiState.kt
package com.cherrish.android.presentation.challenge.routine

import androidx.compose.runtime.Immutable
import com.cherrish.android.presentation.challenge.routine.model.ChallengeRoutineUiModel
import kotlinx.collections.immutable.ImmutableList

@Immutable
data class ChallengeRoutineUiState(
    val routines: ImmutableList<ChallengeRoutineUiModel>,
    val selectedRoutineId: Int? = null,
) {
    val selectedRoutine: ChallengeRoutineUiModel?
        get() = routines.firstOrNull { it.isSelected }

    val isSelected: Boolean
        get() = selectedRoutine != null
}
