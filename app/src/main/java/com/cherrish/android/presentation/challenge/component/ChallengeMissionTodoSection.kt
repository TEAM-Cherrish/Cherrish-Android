package com.cherrish.android.presentation.challenge.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cherrish.android.core.common.extension.dropShadow
import com.cherrish.android.core.designsystem.component.button.CherrishButton
import com.cherrish.android.core.designsystem.theme.CherrishTheme
import com.cherrish.android.presentation.challenge.missionprogress.ChallengeMissionProgressUiState
import com.cherrish.android.presentation.challenge.missionprogress.CherryType
import com.cherrish.android.presentation.challenge.missionprogress.RoutineItemUiState
import com.cherrish.android.presentation.challenge.missionprogress.model.ChallengeInfoModel
import com.cherrish.android.presentation.challenge.missionprogress.model.TemporaryRoutineModel

@Composable
private fun ChallengeMissionTodoList(
    routines: List<RoutineItemUiState>,
    onRoutineClick: (Int) -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            items = routines,
            key = { it.routine.id }
        ) { item ->
            ChallengeChecklist(
                isChecked = item.isCompleted,
                onChecklistClick = { onRoutineClick(item.routine.id) },
                checklistContent = item.routine.name
            )
        }
    }
}

@Composable
fun ChallengeMissionTodoSection(
    uiState: ChallengeMissionProgressUiState,
    onRoutineClick: (Int) -> Unit,
    onCompleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .dropShadow(
                shape = RoundedCornerShape(size = 10.dp),
                blur = 10.dp,
                offsetX = 0.dp,
                offsetY = 0.dp,
                spread = 0.dp,
                color = CherrishTheme.colors.shadow

            )
            .clip(shape = RoundedCornerShape(size = 10.dp))
            .background(color = CherrishTheme.colors.gray0)
            .padding(all = 18.dp)

    ) {
        Text(
            text = "${uiState.currentDay}일차 TO-DO 미션",
            color = CherrishTheme.colors.gray1000,
            style = CherrishTheme.typography.body1SB14,
            modifier = Modifier.padding(bottom = 14.dp)
        )

        ChallengeMissionTodoList(
            routines = uiState.routines,
            onRoutineClick = onRoutineClick
        )

        CherrishButton(
            text = "오늘 미션 종료하기",
            onClick = onCompleteClick,
            enabled = uiState.isCompleteButtonEnabled,
            modifier = Modifier.padding(top = 10.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ChallengeMissionTodoSectionPreview() {
    var uiState by remember {
        mutableStateOf(
            ChallengeMissionProgressUiState(
                challenge = ChallengeInfoModel(
                    id = 1,
                    title = "웰니스 챌린지",
                    totalDays = 7
                ),
                currentDay = 3,
                cherryType = CherryType.PPODUK,
                remainingCount = 2,
                routines = listOf(
                    RoutineItemUiState(
                        routine = TemporaryRoutineModel(1, "dddd"),
                        isCompleted = false
                    ),
                    RoutineItemUiState(
                        routine = TemporaryRoutineModel(2, "미션 1 수행하기"),
                        isCompleted = false
                    ),
                    RoutineItemUiState(
                        routine = TemporaryRoutineModel(3, "미션 2 완료하기"),
                        isCompleted = false
                    )
                )
            )
        )
    }

    ChallengeMissionTodoSection(
        uiState = uiState,
        onRoutineClick = { routineId ->
            uiState = uiState.copy(
                routines = uiState.routines.map {
                    if (it.routine.id == routineId) {
                        it.copy(isCompleted = !it.isCompleted)
                    } else {
                        it
                    }
                }
            )
        },
        onCompleteClick = {},
        modifier = Modifier.padding(20.dp)
    )
}
