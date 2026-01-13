package com.cherrish.android.presentation.calendar.procedure.model

import androidx.compose.ui.graphics.Color

sealed interface ProcedureCardTokens {
    val selectedContainerColor: Color
    val unselectedContainerColor: Color
    val selectedBorderColor: Color
    val unselectedBorderColor: Color
}

data class BasicProcedureCardTokens(
    override val selectedContainerColor: Color,
    override val unselectedContainerColor: Color,
    override val selectedBorderColor: Color,
    override val unselectedBorderColor: Color
) : ProcedureCardTokens

data class SelectableProcedureCardTokens(
    override val selectedContainerColor: Color,
    override val unselectedContainerColor: Color,
    override val selectedBorderColor: Color,
    override val unselectedBorderColor: Color,
    val selectedCheckIconResId: Int,
    val unselectedCheckIconResId: Int
) : ProcedureCardTokens
