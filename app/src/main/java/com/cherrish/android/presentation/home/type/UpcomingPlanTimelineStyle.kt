package com.cherrish.android.presentation.home.type

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.cherrish.android.core.designsystem.theme.CherrishColors
import com.cherrish.android.core.designsystem.theme.gray0

data class UpcomingPlanTimelineStyle(
    val circleColor: Color,
    val barBrush: Brush
)

fun UpcomingPlanTimelineType.style(size: Int, colors: CherrishColors): UpcomingPlanTimelineStyle =
    when (this) {
        UpcomingPlanTimelineType.FIRST -> UpcomingPlanTimelineStyle(
            circleColor = colors.red600,
            barBrush = Brush.linearGradient(
                listOf(colors.red600, if (size >= 2)colors.red500 else colors.gray0)
            )
        )

        UpcomingPlanTimelineType.SECOND -> UpcomingPlanTimelineStyle(
            circleColor = colors.red500,
            barBrush = Brush.linearGradient(
                listOf(colors.red500, if (size >= 3) colors.red300 else colors.gray0)
            )
        )

        UpcomingPlanTimelineType.THIRD -> UpcomingPlanTimelineStyle(
            circleColor = colors.red300,
            barBrush = Brush.linearGradient(listOf(colors.red300, gray0))
        )
    }
