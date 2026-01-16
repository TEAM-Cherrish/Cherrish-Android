package com.cherrish.android.presentation.challenge.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import com.cherrish.android.presentation.challenge.mission.ChallengeMissionUiState
import com.cherrish.android.presentation.challenge.mission.model.ChallengeMissionModel
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList

@Composable
fun ChallengeMissionOnboardingBody(
    items: List<ChallengeMissionUiState>,
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
            itemsIndexed(items = items) { index, item ->
                ChallengeMissionCardChip(
                    text = item.mission.missionContent,
                    isSelected = item.isSelected,
                    onClick = { onItemClick(index) }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ChallengeRoutineOnboardingMissionPreview() {
    CherrishTheme {
        var routineItems by remember {
            mutableStateOf(
                value = persistentListOf(
                    ChallengeMissionUiState(
                        mission = ChallengeMissionModel(
                            id = 1,
                            missionContent = "아침 세안 후 토너 바르기"
                        )
                    ),
                    ChallengeMissionUiState(
                        mission = ChallengeMissionModel(
                            id = 2,
                            missionContent = "수분 에센스 2-3방울 흡수"
                        )
                    ),
                    ChallengeMissionUiState(
                        mission = ChallengeMissionModel(
                            id = 3,
                            missionContent = "보습 크림으로 마무리"
                        )
                    ),
                    ChallengeMissionUiState(
                        mission = ChallengeMissionModel(
                            id = 4,
                            missionContent = "저녁 클렌징 꼼꼼히 하기"
                        )
                    ),
                    ChallengeMissionUiState(
                        mission = ChallengeMissionModel(
                            id = 5,
                            missionContent = "수분 마스크팩 (주 2-3회)"
                        )
                    )
                )
            )
        }

        ChallengeMissionOnboardingBody(
            items = routineItems,
            onItemClick = { clickedIndex ->
                routineItems = routineItems
                    .mapIndexed { index, item ->
                        if (index == clickedIndex) {
                            item.copy(isSelected = !item.isSelected)
                        } else {
                            item
                        }
                    }
                    .toPersistentList()
            },
            modifier = Modifier.padding(26.dp)
        )
    }
}
