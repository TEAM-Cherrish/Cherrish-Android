package com.cherrish.android.core.designsystem.component.gaugebar

import androidx.compose.ui.graphics.Color
import com.cherrish.android.core.designsystem.theme.CherrishColors
import com.cherrish.android.presentation.home.type.CherrishGaugeType

data class CherrishGaugeColors(
    val background: Color,
    val border: Color
)

fun cherrishGaugeActiveColors(
    gaugeType: CherrishGaugeType,
    colors: CherrishColors
): CherrishGaugeColors =
    when (gaugeType) {
        CherrishGaugeType.LEVEL1 -> CherrishGaugeColors(
            background = colors.red300,
            border = colors.red500
        )

        CherrishGaugeType.LEVEL2 -> CherrishGaugeColors(
            background = colors.red400,
            border = colors.red600
        )

        CherrishGaugeType.LEVEL3 -> CherrishGaugeColors(
            background = colors.red500,
            border = colors.red700
        )

        CherrishGaugeType.LEVEL4 -> CherrishGaugeColors(
            background = colors.red600,
            border = colors.red800
        )
    }

fun cherrishGaugeInactiveColors(
    colors: CherrishColors
): CherrishGaugeColors = CherrishGaugeColors(
    background = colors.gray300,
    border = colors.gray500
)
