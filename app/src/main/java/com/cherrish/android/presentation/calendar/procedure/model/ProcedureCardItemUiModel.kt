package com.cherrish.android.presentation.calendar.procedure.model

data class ProcedureCardItemUiModel(
    val id: Long,
    val name: String,
    val category: String,
    val minDowntimeDays: Int,
    val maxDowntimeDays: Int,
    val displayMode: ProcedureCardDisplayMode
)
