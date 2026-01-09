package com.cherrish.android.presentation.calendar.model

data class ProcedureInfoModel(
    val procedureId: Long,
    val procedureName: String,
    val procedureDay: String,
    val downTimeDuration: Int
)