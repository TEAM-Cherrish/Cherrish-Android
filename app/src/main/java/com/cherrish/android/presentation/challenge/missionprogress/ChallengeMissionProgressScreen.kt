package com.cherrish.android.presentation.challenge.missionprogress

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cherrish.android.core.common.state.UiState
import com.cherrish.android.core.designsystem.component.LoadingScreen
import com.cherrish.android.core.designsystem.theme.CherrishTheme
import com.cherrish.android.presentation.challenge.component.ChallengeMissionProgressCherrygrowth
import com.cherrish.android.presentation.challenge.component.ChallengeMissionTodoSection
import com.cherrish.android.presentation.challenge.missionprogress.model.ChallengeRoutineUiModel
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList

@Composable
fun ChallengeMissionProgressRoute(
    paddingValues: PaddingValues,
    viewModel: ChallengeMissionProgressViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (val state = uiState) {
        is UiState.Loading -> {
            LoadingScreen()
        }
        is UiState.Failure -> {}
        is UiState.Success -> {
            ChallengeMissionprogressScreen(
                paddingValues = paddingValues,
                uiState = state.data,
                onTodoClick = viewModel::onTodoClick,
                onCompleteTodayClick = viewModel::onCompletedTodayClick
            )
        }

        else -> {}
    }
}

@Composable
private fun ChallengeMissionprogressScreen(
    paddingValues: PaddingValues,
    uiState: ChallengeMissionProgressUiState,
    onTodoClick: (Long) -> Unit,
    onCompleteTodayClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(CherrishTheme.colors.gray100)
            .padding(paddingValues),
        contentPadding = PaddingValues(top = 38.dp, start = 17.dp, end = 17.dp, bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            ChallengeMissionSelectedTitle(
                challengeName = uiState.challengeName
            )
        }

        item {
            ChallengeMissionProgressCherrygrowth(
                cherryType = uiState.cherryType,
                remainingRoutines = uiState.remainingCount,
                challengeProgress = uiState.progressPercentage
            )
        }

        item {
            ChallengeMissionTodoSection(
                routines = uiState.routines,
                currentDay = uiState.currentDay,
                onRoutineClick = onTodoClick,
                onCompleteClick = onCompleteTodayClick,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun ChallengeMissionSelectedTitle(
    challengeName: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$challengeName 챌린지",
            style = CherrishTheme.typography.title1SB18,
            color = CherrishTheme.colors.gray1000
        )

        Text(
            text = "7일 플랜",
            color = CherrishTheme.colors.gray700,
            style = CherrishTheme.typography.body3M12,
            modifier = Modifier
                .background(
                    color = CherrishTheme.colors.gray100,
                    shape = RoundedCornerShape(4.dp)
                )
                .border(
                    width = 1.dp,
                    color = CherrishTheme.colors.gray700,
                    shape = RoundedCornerShape(4.dp)
                )
                .padding(horizontal = 8.dp, vertical = 3.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ChallengeMissionSelectedTitlePreview() {
    ChallengeMissionSelectedTitle(
        challengeName = "피부 컨디션"
    )
}

@Preview(showBackground = true)
@Composable
private fun ChallengeMissionprogressScreenPreview() {
    var uiState by remember {
        mutableStateOf(
            ChallengeMissionProgressUiState(
                challengeId = 1L,
                challengeName = "피부 컨디션 챌린지",
                currentDay = 3,
                cherryType = CherryType.BBANGBBANG,
                remainingCount = 3,
                progressPercentage = 25,
                routines = persistentListOf(
                    ChallengeRoutineUiModel(
                        routineId = 1L,
                        routineName = "아침 세안 후 토너 바르기",
                        isCompleted = false
                    ),
                    ChallengeRoutineUiModel(
                        routineId = 2L,
                        routineName = "수분 에센스 2–3방울 흡수",
                        isCompleted = false
                    ),
                    ChallengeRoutineUiModel(
                        routineId = 3L,
                        routineName = "보습 크림으로 마무리",
                        isCompleted = false
                    ),
                    ChallengeRoutineUiModel(
                        routineId = 4L,
                        routineName = "외출 전 선크림 꼼꼼히 바르기",
                        isCompleted = false
                    ),
                    ChallengeRoutineUiModel(
                        routineId = 5L,
                        routineName = "태양을 피하는 방법",
                        isCompleted = false
                    ),
                    ChallengeRoutineUiModel(
                        routineId = 6L,
                        routineName = "나가지 않기",
                        isCompleted = false
                    )
                )
            )
        )
    }

    ChallengeMissionprogressScreen(
        paddingValues = PaddingValues(0.dp),
        uiState = uiState,
        onTodoClick = { clickedId ->
            uiState = uiState.copy(
                routines = uiState.routines.map {
                    if (it.routineId == clickedId) {
                        it.copy(isCompleted = !it.isCompleted)
                    } else {
                        it
                    }
                }.toPersistentList()
            )
        },
        onCompleteTodayClick = {}
    )
}
