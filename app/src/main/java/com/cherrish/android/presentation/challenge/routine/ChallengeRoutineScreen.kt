//ChallengeRoutineScreen.kt
package com.cherrish.android.presentation.challenge.routine

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
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
import com.cherrish.android.core.common.extension.collectLatestSideEffect
import com.cherrish.android.core.common.state.UiState
import com.cherrish.android.core.designsystem.component.button.CherrishButton
import com.cherrish.android.core.designsystem.component.topappbar.BackAndCloseTopAppBar
import com.cherrish.android.core.designsystem.theme.CherrishTheme
import com.cherrish.android.presentation.challenge.ChallengeSideEffect
import com.cherrish.android.presentation.challenge.component.ChallengeRoutineOnboardingBody
import com.cherrish.android.presentation.challenge.routine.model.ChallengeRoutineUiModel
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList

@Composable
fun ChallengeRoutineRoute(
    paddingValues: PaddingValues,
    onBackClick: () -> Unit,
    onCloseClick: () -> Unit,
    navigateToChallengeLoading: (Int) -> Unit,
    viewModel: ChallengeRoutineViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    viewModel.sideEffect.collectLatestSideEffect { sideEffect ->
        when (sideEffect) {
            is ChallengeSideEffect.NavigateToChallengeLoading->
                navigateToChallengeLoading(sideEffect.routineId)
        }
    }
    when (val state = uiState) {
        is UiState.Loading -> {
        }

        is UiState.Failure -> {
        }

        is UiState.Success -> {
            ChallengeRoutineScreen(
                uiState = state.data,
                paddingValues = paddingValues,
                onRoutineClick = viewModel::onRoutineClick,
                onNextClick = viewModel::onNextClick,
                onBackClick = onBackClick,
                onCloseClick = onCloseClick
            )
        }

        else -> {}
    }
}

@Composable
private fun ChallengeRoutineScreen(
    paddingValues: PaddingValues,
    uiState: ChallengeRoutineUiState,
    onRoutineClick: (Int) -> Unit,
    onNextClick: () -> Unit,
    onBackClick: () -> Unit,
    onCloseClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val isSelected = uiState.routines.any { it.isSelected }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(CherrishTheme.colors.gray0)
            .padding(paddingValues)
            .navigationBarsPadding()
            .systemBarsPadding()
    ) {
        Spacer(Modifier.height(44.dp))

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
            enabled = isSelected,
            onClick = onNextClick,
            modifier = Modifier.padding(horizontal = 24.dp)
        )

        Spacer(Modifier.height(30.dp))
    }
}

@Preview(showBackground = true)
@Composable
private fun ChallengeRoutineScreenDisabledPreview() {
    var uiState by remember {
        mutableStateOf(
            ChallengeRoutineUiState(
                routines = persistentListOf(
                    ChallengeRoutineUiModel(1, "피부 컨디션"),
                    ChallengeRoutineUiModel(2, "생활 습관"),
                    ChallengeRoutineUiModel(3, "체형 관리"),
                    ChallengeRoutineUiModel(4, "웰니스 · 마음챙김")
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
