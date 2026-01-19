package com.cherrish.android.presentation.challenge.routine

import androidx.compose.runtime.Immutable
import com.cherrish.android.presentation.challenge.routine.model.ChallengeRoutineModel
import kotlinx.collections.immutable.ImmutableList

@Immutable
data class ChallengeRoutineUiState(
    val routines: ImmutableList<ChallengeRoutineModel>
) {
    val selectedRoutine: ChallengeRoutineModel?
        get() = routines.firstOrNull { it.isSelected }

    val isSelected: Boolean
        get() = selectedRoutine != null
}
