package com.cherrish.android.core.designsystem.component.gaugebar

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.cherrish.android.core.designsystem.component.type.CherrishGaugeType
import com.cherrish.android.core.designsystem.theme.CherrishTheme

data class CherrishGaugeStyle(
    val gaugeLabel: String,
    val gaugeLabelColor: Color,
    val gaugeLevelColor: Color,
    val gaugeLevelBorderColor: Color
)

@Composable
fun CherrishGaugeType.style(isActive: Boolean, isSelected: Boolean): CherrishGaugeStyle {
    val gaugeActiveColor = when (this) {
        CherrishGaugeType.LEVEL1 -> Pair(
            CherrishTheme.colors.red300,
            CherrishTheme.colors.red500
        )

        CherrishGaugeType.LEVEL2 -> Pair(
            CherrishTheme.colors.red400,
            CherrishTheme.colors.red600
        )

        CherrishGaugeType.LEVEL3 -> Pair(
            CherrishTheme.colors.red500,
            CherrishTheme.colors.red700
        )

        CherrishGaugeType.LEVEL4 -> Pair(
            CherrishTheme.colors.red600,
            CherrishTheme.colors.red800
        )
    }

    val gaugeInactiveColor = Pair(
        CherrishTheme.colors.gray300,
        CherrishTheme.colors.gray500
    )

    val (gaugeLevelColor, gaugeLevelBorderColor) =
        if (isActive) gaugeActiveColor else gaugeInactiveColor

    val gaugeLabelColor =
        if (isSelected) CherrishTheme.colors.gray900 else CherrishTheme.colors.gray600

    return CherrishGaugeStyle(
        gaugeLabel = "Lv.$step",
        gaugeLabelColor = gaugeLabelColor,
        gaugeLevelColor = gaugeLevelColor,
        gaugeLevelBorderColor = gaugeLevelBorderColor
    )
}
