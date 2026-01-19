package com.cherrish.android.presentation.calendar.procedure.model

import androidx.compose.runtime.Immutable

@Immutable
sealed interface ProcedureFlow {
    data object Entry : ProcedureFlow
    data object Treat : ProcedureFlow
    data object NoTreat : ProcedureFlow
}
