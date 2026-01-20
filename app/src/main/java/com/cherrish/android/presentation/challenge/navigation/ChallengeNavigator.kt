package com.cherrish.android.presentation.challenge.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.cherrish.android.core.common.navigation.MainTabRoute
import com.cherrish.android.core.common.navigation.Route
import com.cherrish.android.presentation.challenge.routine.ChallengeRoutineRoute
import com.cherrish.android.presentation.challenge.start.ChallengeStartRoute
import kotlinx.serialization.Serializable

@Serializable
data object ChallengeStart : MainTabRoute

@Serializable
data object ChallengeRoutine : Route

fun NavController.navigateToChallengeStart(
    navOptions: NavOptions? = null
) {
    navigate(
        route = ChallengeStart,
        navOptions = navOptions
    )
}

fun NavController.navigateToChallengeRoutine(
    navOptions: NavOptions? = null
) {
    navigate(
        route = ChallengeRoutine,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.challengeNavGraph(
    paddingValues: PaddingValues,
    navigateToChallengeRoutine: () -> Unit,
    navigateUp: () -> Unit
) {
    composable<ChallengeStart> {
        ChallengeStartRoute(
            paddingValues = paddingValues,
            onNavigateRoutine = navigateToChallengeRoutine
        )
    }

    composable<ChallengeRoutine> {
        ChallengeRoutineRoute(
            paddingValues = paddingValues,
            onNextClick = {},
            onBackClick = navigateUp,
            onCloseClick = navigateUp,
            navigateToRoutine = navigateToChallengeRoutine
            // 위에꺼 나중에 Todo로 변경
        )
    }
}
