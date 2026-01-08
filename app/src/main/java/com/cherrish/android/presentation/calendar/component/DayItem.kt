package com.cherrish.android.presentation.calendar.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cherrish.android.core.common.extension.noRippleClickable
import com.cherrish.android.core.designsystem.theme.CherrishTheme
import com.cherrish.android.presentation.calendar.model.CalendarDay
import com.cherrish.android.presentation.calendar.model.DownTimeStatus
import java.time.LocalDate
import kotlin.math.min

@Composable
fun DayItem(
    day: CalendarDay,
    isSelected: Boolean,
    onDateClick: (LocalDate) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .aspectRatio(1f)
            .noRippleClickable(
                enabled = day is CalendarDay.Date.Normal,
                onClick = { if (day is CalendarDay.Date) onDateClick(day.date) }
            ),
        contentAlignment = Alignment.Center
    ) {
        when (day) {
            CalendarDay.Empty -> { }

            is CalendarDay.Date.Normal -> NormalDateContent(
                day = day,
                isSelected = isSelected
            )

            is CalendarDay.Date.Downtime -> DowntimeDateContent(
                day = day
            )
        }
    }
}

@Composable
private fun NormalDateContent(
    day: CalendarDay.Date.Normal,
    isSelected: Boolean
) {
    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .clip(shape = RoundedCornerShape(8.dp))
            .background(color = if (isSelected) CherrishTheme.colors.gray0 else Color.Transparent)
            .then(
                if (isSelected) {
                    Modifier.border(
                        width = 1.dp,
                        color = CherrishTheme.colors.gray500,
                        shape = RoundedCornerShape(8.dp)
                    )
                } else {
                    Modifier
                }
            ),
        contentAlignment = Alignment.Center
    ) {
        DateText(day.date.dayOfMonth)

        if (day.procedureCount > 0) {
            ProcedureDots(
                count = day.procedureCount,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 6.dp)
            )
        }
    }
}

@Composable
private fun DowntimeDateContent(
    day: CalendarDay.Date.Downtime
) {
    val colors = getDowntimeColors(day.status)

    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .clip(shape = CircleShape)
            .background(color = colors.background)
            .border(width = 1.dp, color = colors.border, shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        DateText(day.date.dayOfMonth)
    }
}

@Composable
private fun DateText(dayOfMonth: Int) {
    Text(
        text = dayOfMonth.toString(),
        color = CherrishTheme.colors.gray1000,
        style = CherrishTheme.typography.body1R14
    )
}

@Composable
private fun ProcedureDots(
    count: Int,
    modifier: Modifier = Modifier
) {
    val displayCount = min(count, 3)

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        repeat(displayCount) {
            Box(
                modifier = Modifier
                    .size(4.dp)
                    .background(
                        color = CherrishTheme.colors.red700,
                        shape = CircleShape
                    )
            )
        }
    }
}

@Composable
private fun getDowntimeColors(status: DownTimeStatus): DowntimeColors {
    return when (status) {
        DownTimeStatus.CAUTION -> DowntimeColors(
            background = CherrishTheme.colors.red500,
            border = CherrishTheme.colors.red700
        )
        DownTimeStatus.SENSITIVE -> DowntimeColors(
            background = CherrishTheme.colors.red300,
            border = CherrishTheme.colors.red500
        )
        DownTimeStatus.RECOVERY -> DowntimeColors(
            background = CherrishTheme.colors.red200,
            border = CherrishTheme.colors.red400
        )
        DownTimeStatus.NONE -> DowntimeColors(
            background = Color.Transparent,
            border = Color.Transparent
        )
    }
}

private data class DowntimeColors(
    val background: Color,
    val border: Color
)

@Preview(showBackground = true)
@Composable
private fun DayItemPreview() {
    CherrishTheme {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            val date = LocalDate.of(2026, 1, 8)

            DayItem(
                day = CalendarDay.Date.Normal(date, procedureCount = 0),
                isSelected = false,
                onDateClick = {},
                modifier = Modifier.size(48.dp)
            )
            DayItem(
                day = CalendarDay.Date.Normal(date.plusDays(1), procedureCount = 2),
                isSelected = true,
                onDateClick = {},
                modifier = Modifier.size(48.dp)
            )
            DayItem(
                day = CalendarDay.Date.Downtime(date.plusDays(2), status = DownTimeStatus.CAUTION),
                isSelected = false,
                onDateClick = {},
                modifier = Modifier.size(48.dp)
            )
            DayItem(
                day = CalendarDay.Date.Downtime(date.plusDays(3), status = DownTimeStatus.RECOVERY),
                isSelected = false,
                onDateClick = {},
                modifier = Modifier.size(48.dp)
            )
        }
    }
}
