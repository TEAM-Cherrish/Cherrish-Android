package com.cherrish.android.presentation.calendar.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.cherrish.android.core.designsystem.theme.CherrishTheme
import com.cherrish.android.presentation.calendar.util.daysOfWeek
import java.time.DayOfWeek
import java.time.format.TextStyle
import java.util.Locale
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Composable
fun DaysOfWeekTitle(
    daysOfWeek: ImmutableList<DayOfWeek>,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        daysOfWeek.forEach { dayOfWeek ->
            Box(
                modifier = Modifier
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = dayOfWeek.getDisplayName(
                        TextStyle.SHORT,
                        Locale.getDefault()
                    ),
                    textAlign = TextAlign.Center,
                    color = when (dayOfWeek) {
                        DayOfWeek.SUNDAY -> Color.Red
                        DayOfWeek.SATURDAY -> Color.Blue
                        else -> Color.Black
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DaysOfWeekTitlePreview() {
    CherrishTheme {
        val daysOfWeek = daysOfWeek(firstDayOfWeek = DayOfWeek.SUNDAY).toImmutableList()
        DaysOfWeekTitle(daysOfWeek = daysOfWeek)
    }
}
