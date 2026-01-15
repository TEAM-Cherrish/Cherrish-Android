package com.cherrish.android.presentation.calendar.procedure.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.cherrish.android.R
import com.cherrish.android.core.designsystem.theme.CherrishTheme

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

@Composable
fun procedureCardTokens(
    displayMode: ProcedureCardDisplayMode
): ProcedureCardTokens {
    return when (displayMode) {
        ProcedureCardDisplayMode.Basic -> {
            BasicProcedureCardTokens(
                selectedContainerColor = CherrishTheme.colors.gray300,
                unselectedContainerColor = CherrishTheme.colors.gray0,
                selectedBorderColor = CherrishTheme.colors.gray500,
                unselectedBorderColor = CherrishTheme.colors.gray500
            )
        }

        ProcedureCardDisplayMode.Selectable -> {
            SelectableProcedureCardTokens(
                selectedContainerColor = CherrishTheme.colors.green1,
                unselectedContainerColor = CherrishTheme.colors.gray0,
                selectedBorderColor = CherrishTheme.colors.green2,
                unselectedBorderColor = CherrishTheme.colors.gray500,
                selectedCheckIconResId = R.drawable.ic_check_circular_green,
                unselectedCheckIconResId = R.drawable.ic_check_circular
            )
        }
    }
}
