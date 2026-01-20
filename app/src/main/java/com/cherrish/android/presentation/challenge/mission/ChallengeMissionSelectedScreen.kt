package com.cherrish.android.presentation.challenge.mission

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cherrish.android.core.common.state.UiState
import com.cherrish.android.core.designsystem.component.button.CherrishButton
import com.cherrish.android.core.designsystem.component.topappbar.BackAndCloseTopAppBar
import com.cherrish.android.core.designsystem.theme.CherrishTheme
import com.cherrish.android.presentation.challenge.component.ChallengeMissionOnboardingBody
import com.cherrish.android.presentation.challenge.mission.model.ChallengeMissionModel
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList

@Composable
fun ChallengeMissionSelectedRoute(
    paddingValues: PaddingValues,
    navigateToProgress: () -> Unit,
    viewModel: ChallengeMissionViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    when (val state = uiState) {
        is UiState.Loading -> {
        }

        is UiState.Failure -> {
        }

        is UiState.Success -> {
            ChallengeMissionSelectedScreen(
                uiState = state.data,
                paddingValues = paddingValues,
                onBackClick = viewModel::onBackClick,
                onCloseClick = viewModel::onCloseClick,
                onMissionClick = viewModel::onTodoMissionClick,
                onAddTodoClick = viewModel::onAddTodoClick
            )
        }

        else -> {}
    }
}

@Composable
private fun ChallengeMissionSelectedScreen(
    paddingValues: PaddingValues,
    uiState: ChallengeMissionUiState,
    onMissionClick: (Long) -> Unit,
    onAddTodoClick: () -> Unit,
    onBackClick: () -> Unit,
    onCloseClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(CherrishTheme.colors.gray0)
            .padding(paddingValues)
    ) {
        Spacer(Modifier.height(44.dp))

        BackAndCloseTopAppBar(
            title = "TO-DO 미션 선택",
            onBackClick = onBackClick,
            onCloseClick = onCloseClick
        )

        Spacer(Modifier.height(44.dp))

        ChallengeMissionOnboardingBody(
            items = uiState.missions,
            onItemClick = onMissionClick,
            modifier = Modifier.padding(horizontal = 26.dp)
        )

        Spacer(Modifier.weight(64f))

        CherrishButton(
            text = "플래너에 추가하기",
            enabled = uiState.isSelected,
            onClick = onAddTodoClick,
            modifier = Modifier.padding(horizontal = 24.dp)
        )

        Spacer(Modifier.height(30.dp))
    }
}

@Preview(showBackground = true)
@Composable
private fun ChallengeMissionSelectedScreenPreview() {
    var uiState by remember {
        mutableStateOf(
            ChallengeMissionUiState(
                missions = persistentListOf(
                    ChallengeMissionModel(
                        id = 1L,
                        missionContent = "아침 세안 후 토너 바르기",
                        isSelected = false
                    ),
                    ChallengeMissionModel(
                        id = 2L,
                        missionContent = "수분 에센스 2-3방울 흡수",
                        isSelected = false
                    ),
                    ChallengeMissionModel(
                        id = 3L,
                        missionContent = "보습 크림으로 마무리",
                        isSelected = false
                    ),
                    ChallengeMissionModel(
                        id = 4L,
                        missionContent = "저녁 클렌징 꼼꼼히 하기",
                        isSelected = false
                    ),
                    ChallengeMissionModel(
                        id = 5L,
                        missionContent = "수분 마스크팩 (주 2-3회)",
                        isSelected = false
                    )
                )
            )
        )
    }

    ChallengeMissionSelectedScreen(
        paddingValues = PaddingValues(),
        uiState = uiState,
        onMissionClick = { clickedId ->
            uiState = uiState.copy(
                missions = uiState.missions.map { mission ->
                    if (mission.id == clickedId) {
                        mission.copy(isSelected = !mission.isSelected)
                    } else {
                        mission
                    }
                }.toPersistentList()
            )
        },
        onAddTodoClick = {},
        onBackClick = {},
        onCloseClick = {}
    )
}
