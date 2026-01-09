package com.cherrish.android.presentation.calendar.util

import androidx.compose.ui.graphics.Color
import com.cherrish.android.core.designsystem.theme.CherrishColors
import com.cherrish.android.presentation.calendar.model.DownTimeStatus

data class DownTimeColors(
    val background: Color,
    val border: Color
)

fun getDowntimeColors(
    status: DownTimeStatus,
    colors: CherrishColors
): DownTimeColors {
    return when (status) {
        DownTimeStatus.CAUTION -> DownTimeColors(
            background = colors.red500,
            border = colors.red700
        )
        DownTimeStatus.SENSITIVE -> DownTimeColors(
            background = colors.red300,
            border = colors.red500
        )
        DownTimeStatus.RECOVERY -> DownTimeColors(
            background = colors.red200,
            border = colors.red400
        )
        DownTimeStatus.NONE -> DownTimeColors(
            background = Color.Transparent,
            border = Color.Transparent
        )
    }
}
