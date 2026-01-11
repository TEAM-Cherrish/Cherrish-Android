package com.cherrish.android.presentation.calendar.model

import androidx.compose.runtime.Immutable

@Immutable
data class ProcedureInfoModel(
    val procedureId: Long,
    val procedureName: String,
    val procedureDay: String,
    val downTimeDuration: Int?
)
