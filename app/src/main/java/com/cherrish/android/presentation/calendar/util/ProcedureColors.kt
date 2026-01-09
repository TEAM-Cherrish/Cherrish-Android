package com.cherrish.android.presentation.calendar.util

import androidx.compose.ui.graphics.Color
import com.cherrish.android.core.designsystem.theme.CherrishColors
import com.cherrish.android.presentation.calendar.model.ProcedureType

data class ProcedureColors(
    val background: Color,
    val border: Color,
    val text: Color,
    val divider: Color
)

fun getProcedureColors(
    procedureType: ProcedureType,
    colors: CherrishColors
): ProcedureColors {
    return when (procedureType) {
        ProcedureType.ACTIVE -> ProcedureColors(
            background = colors.gray0,
            border = colors.gray500,
            text = colors.gray900,
            divider = colors.red600
        )
        ProcedureType.INACTIVE -> ProcedureColors(
            background = colors.gray100,
            border = colors.gray400,
            text = colors.gray500,
            divider = colors.gray500
        )
    }
}
