package com.cherrish.android.presentation.calendar.procedure.model

import androidx.compose.runtime.Immutable

@Immutable
data class ProcedureWithDowntime(
    val procedureId: Long,
    val downtimeDays: Int
)
