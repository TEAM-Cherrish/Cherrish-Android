package com.cherrish.android.presentation.calendar.procedure.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cherrish.android.core.designsystem.component.textfield.CherrishTextField
import com.cherrish.android.core.designsystem.theme.CherrishTheme
import com.cherrish.android.presentation.calendar.procedure.component.SelectionSection
import kotlinx.collections.immutable.persistentListOf

@Composable
fun RecoveryScheduleContent(
    selectedIndex: Int?,
    onItemClick: (Int) -> Unit,
    year: String,
    month: String,
    day: String,
    onYearChange: (String) -> Unit,
    onMonthChange: (String) -> Unit,
    onDayChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val hasSelection = selectedIndex != null && selectedIndex >= 0

    Column(modifier = modifier) {
        SelectionSection(
            title = "회복을 계획할 때 고려해야 할\n중요한 일정이 있나요?",
            items = persistentListOf("아직 없어요", "네, 있어요"),
            selectedIndex = selectedIndex,
            onItemClick = onItemClick
        )

        if (hasSelection) {
            Column(
                modifier = Modifier.padding(top = 56.dp)
            ) {
                val sectionTitle = if (selectedIndex == 0) {
                    "대략적인 회복 목표일을 정해볼까요?"
                } else {
                    "언제까지 회복이 완료되면 좋을까요?"
                }

                ScheduleSettingSection(
                    title = sectionTitle,
                    year = year,
                    month = month,
                    day = day,
                    onYearChange = onYearChange,
                    onMonthChange = onMonthChange,
                    onDayChange = onDayChange
                )
            }
        }
    }
}

@Composable
private fun ScheduleSettingSection(
    title: String,
    year: String,
    month: String,
    day: String,
    onYearChange: (String) -> Unit,
    onMonthChange: (String) -> Unit,
    onDayChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = title,
            style = CherrishTheme.typography.title1SB18,
            color = CherrishTheme.colors.gray1000
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "날짜",
            style = CherrishTheme.typography.body1SB14,
            color = CherrishTheme.colors.gray1000
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            DateInputBasicSection(
                date = year,
                suffix = "년",
                placeholder = "YYYY",
                onValueChange = onYearChange,
                modifier = Modifier.weight(1f)
            )
            DateInputBasicSection(
                date = month,
                suffix = "월",
                placeholder = "MM",
                onValueChange = onMonthChange,
                modifier = Modifier.weight(1f)
            )
            DateInputBasicSection(
                date = day,
                suffix = "일",
                placeholder = "DD",
                onValueChange = onDayChange,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun DateInputBasicSection(
    date: String,
    suffix: String,
    placeholder: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = CenterVertically
    ) {
        CherrishTextField(
            value = date,
            placeholder = placeholder,
            onValueChange = onValueChange,
            roundedCornerShape = RoundedCornerShape(8.dp),
            placeholderTextStyle = CherrishTheme.typography.title2R16.copy(
                textAlign = TextAlign.Center
            ),
            placeholderTextColor = CherrishTheme.colors.gray500,
            inputTextStyle = CherrishTheme.typography.title2M16.copy(
                textAlign = TextAlign.Center
            ),
            inputTextColor = CherrishTheme.colors.gray800,
            paddingValues = PaddingValues(horizontal = 19.dp, vertical = 8.dp),
            modifier = Modifier.weight(1f)
        )

        Text(
            text = suffix,
            style = CherrishTheme.typography.title2M16,
            color = CherrishTheme.colors.gray700
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun RecoveryScheduleContentPreview() {
    CherrishTheme {
        var selectedIndex by remember { mutableIntStateOf(-1) }
        var year by remember { mutableStateOf("") }
        var month by remember { mutableStateOf("") }
        var day by remember { mutableStateOf("") }

        RecoveryScheduleContent(
            selectedIndex = selectedIndex,
            onItemClick = { selectedIndex = it },
            year = year,
            month = month,
            day = day,
            onYearChange = { year = it },
            onMonthChange = { month = it },
            onDayChange = { day = it }
        )
    }
}
