package com.cherrish.android.presentation.calendar.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        daysOfWeek.forEach { dayOfWeek ->
            key(dayOfWeek) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = dayOfWeek.getDisplayName(
                            TextStyle.SHORT,
                            Locale.getDefault()
                        ),
                        color = CherrishTheme.colors.gray800,
                        style = CherrishTheme.typography.body1R14
                    )
                }
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
