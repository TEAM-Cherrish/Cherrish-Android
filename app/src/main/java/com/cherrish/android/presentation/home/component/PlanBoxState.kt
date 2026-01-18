package com.cherrish.android.presentation.home.component

import com.cherrish.android.presentation.home.type.DowntimePhase

sealed interface PlanBoxState {
    data object Empty : PlanBoxState
    data class Filled(
        val medicalProcedureName: String,
        val medicalProcedureNameDate: Int,
        val downtimePhase: DowntimePhase
    ) : PlanBoxState
}
