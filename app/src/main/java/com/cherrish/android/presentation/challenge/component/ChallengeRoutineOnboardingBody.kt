package com.cherrish.android.presentation.challenge.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cherrish.android.core.designsystem.component.chip.CherrishSelectionChip
import com.cherrish.android.core.designsystem.theme.CherrishTheme

@Composable
fun ChallengeRoutineOnboardingBody(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = "지금 나에게 가장 필요한 \n관리 루틴을 선택해주세요.",
            modifier = Modifier,
            color = CherrishTheme.colors.gray1000,
            style = CherrishTheme.typography.title1SB18
        )
        Spacer(modifier = Modifier.padding(vertical = 40.dp))
        ChallengeRoutineSelectionChipGroup(modifier = Modifier)
    }
}

@Composable
private fun ChallengeRoutineSelectionChipGroup(modifier: Modifier = Modifier) {
    var isSelected by remember { mutableStateOf(value = false) } // 추후 viewmodel 생기면 변경 예정

    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(space = 12.dp)) {
        Row(modifier = Modifier, horizontalArrangement = Arrangement.spacedBy(space = 12.dp)) {
            CherrishSelectionChip(
                text = "피부 컨디션",
                onClick = { isSelected = !isSelected },
                modifier = Modifier.weight(weight = 1f),
                isSelected = isSelected
            )
            CherrishSelectionChip(
                text = "생활 습관",
                onClick = { isSelected = !isSelected },
                modifier = Modifier.weight(weight = 1f),
                isSelected = isSelected
            )
        }

        Row(modifier = Modifier, horizontalArrangement = Arrangement.spacedBy(space = 12.dp)) {
            CherrishSelectionChip(
                text = "체형 관리",
                onClick = { isSelected = !isSelected },
                modifier = Modifier.weight(weight = 1f),
                isSelected = isSelected
            )
            CherrishSelectionChip(
                text = "웰니스 ∙ 마음 챙김",
                onClick = { isSelected = !isSelected },
                modifier = Modifier.weight(weight = 1f),
                isSelected = isSelected
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ChallengeRoutineOnboardingBodyPreview() {
    ChallengeRoutineOnboardingBody()
}

@Preview(showBackground = true)
@Composable
private fun ChallengeRoutineMissionCardGroupPreview() {
    ChallengeRoutineSelectionChipGroup()
}
