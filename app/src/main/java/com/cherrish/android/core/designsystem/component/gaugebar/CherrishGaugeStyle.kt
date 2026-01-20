package com.cherrish.android.core.designsystem.component.gaugebar

import androidx.compose.ui.graphics.Color
import com.cherrish.android.core.designsystem.theme.CherrishColors
import com.cherrish.android.presentation.home.type.CherrishGaugeType

data class CherrishGaugeStyle(
    val gaugeLabel: String,
    val gaugeLabelColor: Color,
    val gaugeLevelColor: Color,
    val gaugeLevelBorderColor: Color
)

fun CherrishGaugeType.style(
    colors: CherrishColors,
    isActive: Boolean,
    isSelected: Boolean
): CherrishGaugeStyle {
    val gaugeActiveColor = cherrishGaugeActiveColors(
        gaugeType = this,
        colors = colors
    )

    val gaugeInactiveColor = cherrishGaugeInactiveColors(
        colors = colors
    )

    val (gaugeLevelColor, gaugeLevelBorderColor) =
        if (isActive) gaugeActiveColor else gaugeInactiveColor

    val gaugeLabelColor =
        if (isSelected) colors.gray900 else colors.gray600

    return CherrishGaugeStyle(
        gaugeLabel = "Lv.$step",
        gaugeLabelColor = gaugeLabelColor,
        gaugeLevelColor = gaugeLevelColor,
        gaugeLevelBorderColor = gaugeLevelBorderColor
    )
}
