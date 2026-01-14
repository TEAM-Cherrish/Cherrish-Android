package com.cherrish.android.presentation.calendar.util

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import com.cherrish.android.core.designsystem.theme.CherrishColors
import com.cherrish.android.presentation.calendar.model.CalendarDisplayMode
import com.cherrish.android.presentation.calendar.model.ProcedureType

@Immutable
data class ProcedureColors(
    val background: Color,
    val border: Color,
    val procedureNameText: Color,
    val procedureDateText: Color,
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
            procedureNameText = colors.gray900,
            procedureDateText = colors.gray800,
            divider = colors.red600
        )
        ProcedureType.ACTIVE_NO_DOWNTIME -> ProcedureColors(
            background = colors.gray0,
            border = colors.gray500,
            procedureNameText = colors.gray900,
            procedureDateText = colors.gray800,
            divider = colors.gray500
        )
        ProcedureType.INACTIVE -> ProcedureColors(
            background = colors.gray100,
            border = Color.Transparent,
            procedureNameText = colors.gray500,
            procedureDateText = colors.gray500,
            divider = colors.gray300
        )
    }
}

fun getProcedureType(
    displayMode: CalendarDisplayMode,
    procedureId: Long,
    downTimeDuration: Int
): ProcedureType = when (displayMode) {
    is CalendarDisplayMode.Normal -> {
        if (downTimeDuration == 0) {
            ProcedureType.ACTIVE_NO_DOWNTIME
        } else {
            ProcedureType.ACTIVE
        }
    }
    is CalendarDisplayMode.Downtime -> {
        if (displayMode.selectedProcedureId == procedureId) {
            ProcedureType.ACTIVE
        } else {
            ProcedureType.INACTIVE
        }
    }
}
