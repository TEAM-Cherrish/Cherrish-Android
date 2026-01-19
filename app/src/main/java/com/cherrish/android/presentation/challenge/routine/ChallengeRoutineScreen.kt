package com.cherrish.android.presentation.challenge.routine

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cherrish.android.core.common.state.UiState
import com.cherrish.android.core.designsystem.component.button.CherrishButton
import com.cherrish.android.core.designsystem.component.topappbar.BackAndCloseTopAppBar
import com.cherrish.android.core.designsystem.theme.CherrishTheme
import com.cherrish.android.presentation.challenge.component.ChallengeRoutineOnboardingBody
import com.cherrish.android.presentation.challenge.routine.model.ChallengeRoutineModel
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList

@Composable
fun ChallengeRoutineRoute(
    paddingValues: PaddingValues,
    viewModel: ChallengeRoutineViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (uiState) {
        is UiState.Loading -> Unit
        is UiState.Failure -> Unit
        is UiState.Success -> {
            ChallengeRoutineScreen(
                paddingValues = paddingValues,
                uiState = (uiState as UiState.Success<ChallengeRoutineUiState>).data,
                onRoutineClick = viewModel::onRoutineClick,
                onNextClick = viewModel::onNextClick,
                onBackClick = {},
                onCloseClick = {}
            )
        }

        else -> {}
    }
}

@Composable
private fun ChallengeRoutineScreen(
    paddingValues: PaddingValues,
    uiState: ChallengeRoutineUiState,
    onRoutineClick: (Long) -> Unit,
    onNextClick: () -> Unit,
    onBackClick: () -> Unit,
    onCloseClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val hasSelected = uiState.routines.any { it.isSelected }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(CherrishTheme.colors.gray0)
            .padding(paddingValues)
    ) {
        Spacer(Modifier.weight(44f))

        BackAndCloseTopAppBar(
            title = "루틴 챌린지 선택",
            onBackClick = onBackClick,
            onCloseClick = onCloseClick
        )

        Spacer(Modifier.weight(70f))

        ChallengeRoutineOnboardingBody(
            items = uiState.routines,
            onItemClick = onRoutineClick,
            modifier = Modifier.padding(horizontal = 26.dp)
        )

        Spacer(Modifier.weight(232f))

        CherrishButton(
            text = "다음",
            enabled = hasSelected,
            onClick = onNextClick,
            modifier = Modifier.padding(horizontal = 24.dp)
        )

        Spacer(Modifier.weight(30f))
    }
}

@Preview(showBackground = true)
@Composable
private fun ChallengeRoutineScreenDisabledPreview() {
    var uiState by remember {
        mutableStateOf(
            ChallengeRoutineUiState(
                routines = persistentListOf(
                    ChallengeRoutineModel(1L, "피부 컨디션"),
                    ChallengeRoutineModel(2L, "생활 습관"),
                    ChallengeRoutineModel(3L, "체형 관리"),
                    ChallengeRoutineModel(4L, "웰니스 · 마음챙김")
                )
            )
        )
    }

    ChallengeRoutineScreen(
        paddingValues = PaddingValues(),
        uiState = uiState,
        onRoutineClick = { id ->
            uiState = uiState.copy(
                routines = uiState.routines.map {
                    it.copy(isSelected = it.id == id)
                }.toPersistentList()
            )
        },
        onNextClick = {},
        onBackClick = {},
        onCloseClick = {}
    )
}
