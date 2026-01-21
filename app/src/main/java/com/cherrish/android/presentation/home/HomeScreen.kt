package com.cherrish.android.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cherrish.android.core.common.extension.collectLatestSideEffect
import com.cherrish.android.core.common.state.UiState
import com.cherrish.android.core.designsystem.component.LoadingScreen
import com.cherrish.android.core.designsystem.theme.CherrishTheme
import com.cherrish.android.core.designsystem.theme.graEnd
import com.cherrish.android.core.designsystem.theme.graStart
import com.cherrish.android.presentation.home.component.ChallengeSection
import com.cherrish.android.presentation.home.component.PlanBoxSection
import com.cherrish.android.presentation.home.component.UpcomingPlanSection
import java.time.LocalDate
import kotlinx.collections.immutable.persistentListOf

@Composable
fun HomeRoute(
    paddingValues: PaddingValues,
    navigateToChallenge: () -> Unit,
    navigateToCalendar: (LocalDate) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    viewModel.sideEffect.collectLatestSideEffect { sideEffect ->
        when (sideEffect) {
            is HomeSideEffect.NavigateToChallenge -> {
                navigateToChallenge()
            }
            is HomeSideEffect.NavigateToCalendar -> {
                navigateToCalendar(sideEffect.date)
            }
        }
    }

    when (val state = uiState) {
        is UiState.Loading -> {
            LoadingScreen()
        }

        is UiState.Failure -> {
        }

        is UiState.Success -> {
            HomeScreen(
                uiState = state.data,
                paddingValues = paddingValues,
                onUpcomingPlanClick = viewModel::onUpcomingPlanClick,
                onChallengeStartClick = viewModel::onAddChallengeClick,
                onAddPlanClick = viewModel::onAddPlanClick
            )
        }

        else -> {}
    }
}

@Composable
private fun HomeScreen(
    uiState: HomeUiState,
    paddingValues: PaddingValues,
    onUpcomingPlanClick: (LocalDate) -> Unit,
    onChallengeStartClick: () -> Unit,
    onAddPlanClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = CherrishTheme.colors.graEnd)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(270.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = persistentListOf(
                            graStart,
                            graEnd
                        )
                    )
                )
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(
                start = 17.dp,
                end = 17.dp,
                top = 30.dp,
                bottom = 20.dp
            ),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                ChallengeSection(
                    imageRes = uiState.gauges[uiState.selectedIndex].image,
                    currentStep = uiState.currentStep,
                    gauges = uiState.gauges,
                    onChallengeStartClick = onChallengeStartClick,
                    challengeName = uiState.challengeName,
                    challengeRate = uiState.challengeRate
                )
            }

            item {
                PlanBoxSection(
                    todayDate = uiState.todayDate,
                    plans = uiState.plans
                )
            }

            item {
                UpcomingPlanSection(
                    onAddPlanClick = onAddPlanClick,
                    plans = uiState.upcomingPlans,
                    onUpcomingPlanClick = onUpcomingPlanClick
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    CherrishTheme {
        HomeScreen(
            uiState = HomeUiState.fake,
            paddingValues = PaddingValues(0.dp),
            onUpcomingPlanClick = {},
            onAddPlanClick = {},
            onChallengeStartClick = {}
        )
    }
}
