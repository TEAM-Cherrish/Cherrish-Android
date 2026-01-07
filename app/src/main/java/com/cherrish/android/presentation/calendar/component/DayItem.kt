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
import com.cherrish.android.presentation.calendar.model.CalendarDay
import com.cherrish.android.presentation.calendar.model.DownTimeStatus
import java.time.LocalDate
import kotlin.math.min

@Composable
fun DayItem(
    day: CalendarDay,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    showDowntime: Boolean = false
) {
    Box(
        modifier = modifier
            .aspectRatio(1f)
            .noRippleClickable(
                enabled = day is CalendarDay.Date,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        when (day) {
            CalendarDay.Empty -> { }

            is CalendarDay.Date -> {
                val backgroundColor = when {
                    showDowntime && day.downtimeStatus != null -> {
                        getDowntimeColor(day.downtimeStatus)
                    }
                    isSelected -> Color(0xFFE5E5E5)
                    else -> Color.Transparent
                }

                val borderModifier = when {
                    showDowntime -> Modifier
                    isSelected -> Modifier.border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(8.dp)
                    )
                    else -> Modifier
                }

                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(if (showDowntime) CircleShape else RoundedCornerShape(8.dp))
                        .background(backgroundColor)
                        .then(borderModifier),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = day.date.dayOfMonth.toString(),
                        color = Color.Black
                    )

                    if (!showDowntime && day.procedureCount > 0) {
                        ProcedureDots(
                            count = day.procedureCount,
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .padding(bottom = 4.dp)
                        )
                    }
                }
            }
        }
    }
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
                        color = Color(0xFFFF6B9D),
                        shape = CircleShape
                    )
            )
        }
    }
}

private fun getDowntimeColor(status: DownTimeStatus): Color {
    return when (status) {
        DownTimeStatus.CAUTION -> Color(0xFFFF6B9D)
        DownTimeStatus.SENSITIVE -> Color(0xFFFFB3D1)
        DownTimeStatus.RECOVERY -> Color(0xFFFFDCEB)
    }
}

@Preview(showBackground = true)
@Composable
private fun DayItemPreviewRow() {
    Row(
        modifier = Modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        val days = listOf(
            CalendarDay.Empty,
            CalendarDay.Empty,
            CalendarDay.Date(LocalDate.of(2025, 1, 1), procedureCount = 1),
            CalendarDay.Date(LocalDate.of(2025, 1, 2), procedureCount = 0),
            CalendarDay.Date(LocalDate.of(2025, 1, 3), procedureCount = 3),
            CalendarDay.Date(LocalDate.of(2025, 1, 4), procedureCount = 2),
            CalendarDay.Date(LocalDate.of(2025, 1, 5), procedureCount = 0)
        )

        days.forEach { day ->
            DayItem(
                day = day,
                isSelected = day is CalendarDay.Date && day.date.dayOfMonth == 3,
                onClick = {},
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DayItemDowntimePreview() {
    Row(
        modifier = Modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        val days = listOf(
            CalendarDay.Date(
                LocalDate.of(2025, 1, 7),
                downtimeStatus = DownTimeStatus.CAUTION
            ),
            CalendarDay.Date(
                LocalDate.of(2025, 1, 8),
                downtimeStatus = DownTimeStatus.CAUTION
            ),
            CalendarDay.Date(
                LocalDate.of(2025, 1, 9),
                downtimeStatus = DownTimeStatus.SENSITIVE
            ),
            CalendarDay.Date(
                LocalDate.of(2025, 1, 10),
                downtimeStatus = DownTimeStatus.RECOVERY
            )
        )

        days.forEach { day ->
            DayItem(
                day = day,
                onClick = {},
                showDowntime = true,
                modifier = Modifier.weight(1f)
            )
        }
    }
}
