package com.cherrish.android.presentation.calendar.procedure.model

import androidx.compose.runtime.Immutable

@Immutable
data class SelectedProcedureModel(
    val procedureId: Long,
    val procedureName: String,
    val minDowntimeDays: Int,
    val maxDowntimeDays: Int
)
