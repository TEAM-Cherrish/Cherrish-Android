package com.cherrish.android.presentation.calendar.procedure.model

sealed interface ProcedureCardDisplayMode {
    data object Basic : ProcedureCardDisplayMode
    data object Selectable : ProcedureCardDisplayMode
}
