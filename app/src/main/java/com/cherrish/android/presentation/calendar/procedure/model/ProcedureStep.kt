package com.cherrish.android.presentation.calendar.procedure.model

import androidx.compose.runtime.Immutable

@Immutable
sealed interface ProcedureStep {
    data object Category : ProcedureStep
    data object RecoverySchedule : ProcedureStep
    data object Downtime : ProcedureStep
    data object Filtering : ProcedureStep
    data object FilteringWithSearch : ProcedureStep
}
