package com.cherrish.android.presentation.challenge.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.cherrish.android.data.model.ChallengeRoutineResponseModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList

@Composable
fun ChallengeMissionTodoSection(
    routines: ImmutableList<ChallengeRoutineResponseModel>,
    currentDay: Int,
    onRoutineClick: (Long) -> Unit,
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
        ChallengeMissionTodoTitle(
            currentDay = currentDay,
            modifier = Modifier.padding(bottom = 14.dp)
        )

        ChallengeMissionTodoList(
            routines = routines,
            onRoutineClick = onRoutineClick
        )

        CherrishButton(
            text = "오늘 미션 종료하기",
            onClick = onCompleteClick,

            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
        )
    }
}

@Composable
private fun ChallengeMissionTodoList(
    routines: ImmutableList<ChallengeRoutineResponseModel>,
    onRoutineClick: (Long) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        routines.forEach { item ->
            ChallengeChecklist(
                isChecked = item.isCompleted,
                onChecklistClick = { onRoutineClick(item.routineId) },
                checklistContent = item.routineName
            )
        }
    }
}

@Composable
private fun ChallengeMissionTodoTitle(
    currentDay: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = "${currentDay}일차",
            color = CherrishTheme.colors.gray1000,
            style = CherrishTheme.typography.body1SB14
        )

        Text(
            text = "TO-DO 미션",
            color = CherrishTheme.colors.gray1000,
            style = CherrishTheme.typography.body1SB14
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ChallengeMissionTodoSectionPreview() {
    var routines by remember {
        mutableStateOf<ImmutableList<ChallengeRoutineResponseModel>>(
            persistentListOf(
                ChallengeRoutineResponseModel(
                    routineId = 1,
                    routineName = "선크림 바르기",
                    scheduledDate = "",
                    isCompleted = false
                ),
                ChallengeRoutineResponseModel(
                    routineId = 2,
                    routineName = "진정 토너 + 세럼",
                    scheduledDate = "",
                    isCompleted = false
                ),
                ChallengeRoutineResponseModel(
                    routineId = 3,
                    routineName = "미끄덩 거리는 로션",
                    scheduledDate = "",
                    isCompleted = false
                )
            )
        )
    }

    ChallengeMissionTodoSection(
        routines = routines,
        currentDay = 5,
        onRoutineClick = { routineId ->
            routines = routines
                .map {
                    if (it.routineId == routineId) {
                        it.copy(isCompleted = !it.isCompleted)
                    } else {
                        it
                    }
                }
                .toImmutableList()
        },
        onCompleteClick = {},
        modifier = Modifier.padding(20.dp)
    )
}
