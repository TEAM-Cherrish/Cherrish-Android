package com.cherrish.android.presentation.challenge.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cherrish.android.core.designsystem.theme.CherrishTheme
import com.cherrish.android.presentation.challenge.mission.model.ChallengeMissionModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun ChallengeMissionOnboardingBody(
    items: ImmutableList<ChallengeMissionModel>,
    selectedMissionIds: Set<Int>,
    onItemClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "챌린지 기간 동안\n진행할 미션을 선택해주세요.",
            color = CherrishTheme.colors.gray1000,
            style = CherrishTheme.typography.title1SB18
        )

        Spacer(modifier = Modifier.height(height = 4.dp))

        Text(
            text = "복수 선택이 가능해요.",
            color = CherrishTheme.colors.gray700,
            style = CherrishTheme.typography.body1R14
        )

        Spacer(modifier = Modifier.height(height = 30.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(space = 8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(
                items = items,
                key = { it.id }
            ) {
                    item ->
                ChallengeMissionCardChip(
                    text = item.missionContent,
                    isSelected = item.id in selectedMissionIds,
                    onClick = { onItemClick(item.id) }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ChallengeRoutineOnboardingMissionPreview() {
    CherrishTheme {
        var selectedMissionIds by remember { mutableStateOf<Set<Int>>(emptySet()) }

        val missions = persistentListOf(
            ChallengeMissionModel(1, "아침 세안 후 토너 바르기"),
            ChallengeMissionModel(2, "수분 에센스 2-3방울 흡수"),
            ChallengeMissionModel(3, "보습 크림으로 마무리"),
            ChallengeMissionModel(4, "저녁 클렌징 꼼꼼히 하기"),
            ChallengeMissionModel(5, "수분 마스크팩 (주 2-3회)")
        )

        ChallengeMissionOnboardingBody(
            items = missions,
            selectedMissionIds = selectedMissionIds,
            onItemClick = { clickedId ->
                selectedMissionIds =
                    if (clickedId in selectedMissionIds) {
                        selectedMissionIds - clickedId
                    } else {
                        selectedMissionIds + clickedId
                    }
            },
            modifier = Modifier.padding(26.dp)
        )
    }
}
