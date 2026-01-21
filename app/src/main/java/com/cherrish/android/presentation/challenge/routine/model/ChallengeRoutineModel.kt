//ChallengeRoutineUiModel.kt
package com.cherrish.android.presentation.challenge.routine.model

import androidx.compose.runtime.Immutable
import com.cherrish.android.data.model.ChallengeHomecareRoutinesResponseModel

@Immutable
data class ChallengeRoutineUiModel(
    val id: Int,
    val routine: String,
    val isSelected: Boolean = false
)

fun ChallengeHomecareRoutinesResponseModel.toUiModel() =
    ChallengeRoutineUiModel(
        id = id,
        routine = description
    )
