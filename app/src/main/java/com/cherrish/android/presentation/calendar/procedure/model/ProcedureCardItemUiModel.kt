package com.cherrish.android.presentation.calendar.procedure.model

import androidx.compose.runtime.Immutable

@Immutable
data class ProcedureCardItemUiModel(
    val id: Long,
    val name: String,
    val category: String,
    val minDowntimeDays: Int,
    val maxDowntimeDays: Int,
    val displayMode: ProcedureCardDisplayMode
)
