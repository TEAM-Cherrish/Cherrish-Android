package com.cherrish.android.presentation.challenge.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cherrish.android.core.designsystem.component.chip.CherrishMissionCard
import com.cherrish.android.core.designsystem.component.chip.CherrishSelectionChip
import com.cherrish.android.core.designsystem.theme.CherrishTheme
import com.cherrish.android.presentation.challenge.model.ChallengeRoutineCategory
import com.cherrish.android.presentation.challenge.model.ChallengeRoutineItem
import com.cherrish.android.presentation.challenge.model.ChallengeRoutineMissionModel
import com.cherrish.android.presentation.challenge.model.ChallengeRoutineModel

@Composable
fun ChallengeRoutineOnboardingBody(
    items : List<ChallengeRoutineItem>,
    onItemClick: (ChallengeRoutineItem) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "지금 나에게 가장 필요한 \n관리 루틴을 선택해주세요.",
            color = CherrishTheme.colors.gray1000,
            style = CherrishTheme.typography.title1SB18
        )

        Spacer(modifier = Modifier.height(height = 40.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxHeight()
        ) {
            items(items) { item ->
                when (item) {
                    is ChallengeRoutineModel -> {
                        CherrishSelectionChip(
                            text = item.title,
                            isSelected = item.isSelected,
                            onClick = { onItemClick(item) }
                        )
                    }

                    is ChallengeRoutineMissionModel -> {
                        CherrishMissionCard(
                            text = item.title,
                            isSelected = item.isSelected,
                            onClick = { onItemClick(item) }
                        )
                    }

                }
            }
        }
    }
}
@Preview(
    name = "Routine Select Preview",
    showBackground = true
)
@Composable
private fun ChallengeRoutineOnboarding_RoutinePreview() {
    CherrishTheme {

        var routineItems by remember {
            mutableStateOf(
                listOf(
                    ChallengeRoutineModel(
                        id = 1,
                        title = "보습 루틴",
                        category = "SKIN"
                    ),
                    ChallengeRoutineModel(
                        id = 2,
                        title = "진정 루틴",
                        category = "SKIN"
                    ),
                    ChallengeRoutineModel(
                        id = 3,
                        title = "미백 루틴",
                        category = "SKIN"
                    ),
                    ChallengeRoutineModel(
                        id = 4,
                        title = "탄력 루틴",
                        category = "SKIN"
                    ),
                    ChallengeRoutineModel(
                        id = 5,
                        title = "모공 관리 루틴",
                        category = "SKIN"
                    ),
                    ChallengeRoutineModel(
                        id = 6,
                        title = "트러블 케어 루틴",
                        category = "SKIN"
                    )
                )
            )
        }

        ChallengeRoutineOnboardingBody(
            items = routineItems,
            onItemClick = { clickedItem ->
                routineItems = routineItems.map { item ->
                    item.copy(
                        isSelected = item.id == clickedItem.id
                    )
                }
            },
            modifier = Modifier.padding(26.dp)
        )
    }
}


@Preview(
    name = "Mission Select Preview",
    showBackground = true
)
@Composable
private fun ChallengeRoutineOnboarding_MissionPreview() {
    CherrishTheme {

        var missionItems by remember {
            mutableStateOf(
                listOf(
                    ChallengeRoutineMissionModel(
                        id = 1,
                        title = "아침 세안 후 토너 바르기",
                        subTitle = "기초 수분 공급",
                        category = "SKIN"
                    ),
                    ChallengeRoutineMissionModel(
                        id = 2,
                        title = "수분 에센스 2-3방울 흡수",
                        subTitle = "속건조 케어",
                        category = "SKIN"
                    ),
                    ChallengeRoutineMissionModel(
                        id = 3,
                        title = "보습 크림으로 마무리",
                        subTitle = "수분 잠금",
                        category = "SKIN"
                    ),
                    ChallengeRoutineMissionModel(
                        id = 4,
                        title = "저녁 클렌징 꼼꼼히 하기",
                        subTitle = "노폐물 제거",
                        category = "SKIN"
                    ),
                    ChallengeRoutineMissionModel(
                        id = 5,
                        title = "수분 마스크팩",
                        subTitle = "주 2-3회",
                        category = "SKIN"
                    )
                )
            )
        }

        ChallengeRoutineOnboardingBody(
            items = missionItems,
            onItemClick = { clickedItem ->
                missionItems = missionItems.map { item ->
                    if (item.id == clickedItem.id) {
                        item.copy(isSelected = !item.isSelected)
                    } else {
                        item
                    }
                }
            },
            modifier = Modifier.padding(26.dp)
        )
    }
}
