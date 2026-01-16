package com.cherrish.android.presentation.challenge.missionprogress.model

import androidx.compose.runtime.Immutable

@Immutable
data class DailyTodoRoutineModel(
    val id: Long,
    val name: String,
    val isCompleted: Boolean
)
