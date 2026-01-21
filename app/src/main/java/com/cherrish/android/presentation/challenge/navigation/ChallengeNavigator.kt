package com.cherrish.android.presentation.challenge.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.cherrish.android.core.common.navigation.MainTabRoute
import com.cherrish.android.core.common.navigation.Route
import com.cherrish.android.presentation.challenge.loading.ChallengeLoadingRoute
import com.cherrish.android.presentation.challenge.mission.ChallengeMissionSelectedRoute
import com.cherrish.android.presentation.challenge.missionprogress.ChallengeMissionProgressRoute
import com.cherrish.android.presentation.challenge.routine.ChallengeRoutineRoute
import com.cherrish.android.presentation.challenge.start.ChallengeStartRoute
import kotlinx.serialization.Serializable

@Serializable
data object ChallengeStart : MainTabRoute

@Serializable
data object ChallengeRoutine : Route

@Serializable
data class ChallengeLoading(
    val routineId: Int
) : Route

@Serializable
data class ChallengeMission(
    val routineId: Int,
    val routines: List<String>
) : Route

@Serializable
data object ChallengeProgress : Route

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

fun NavController.navigateToChallengeLoading(
    routineId: Int,
    navOptions: NavOptions? = null
) {
    navigate(
        route = ChallengeLoading(routineId = routineId),
        navOptions = navOptions
    )
}

fun NavController.navigateToChallengeMission(
    routineId: Int,
    routines: List<String>,
    navOptions: NavOptions? = null
) {
    navigate(
        route = ChallengeMission(
            routineId = routineId,
            routines = routines
        ),
        navOptions = navOptions
    )
}

fun NavController.navigateToChallengeMissionProgress(
    navOptions: NavOptions? = null
) {
    navigate(
        route = ChallengeProgress,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.challengeNavGraph(
    paddingValues: PaddingValues,
    navigateToChallengeRoutine: () -> Unit,
    navigateToChallengeMission: (Int, List<String>) -> Unit,
    navigateToChallengeLoading: (Int) -> Unit,
    navigateToChallengeMissionProgress: () -> Unit,
    navigateToChallengeStart: () -> Unit,
    navigateUp: () -> Unit
) {
    composable<ChallengeStart> {
        ChallengeStartRoute(
            paddingValues = paddingValues,
            onNavigateToRoutine = navigateToChallengeRoutine
        )
    }

    composable<ChallengeRoutine> {
        ChallengeRoutineRoute(
            paddingValues = paddingValues,
            onBackClick = navigateUp,
            onCloseClick = navigateUp,
            navigateToChallengeLoading = navigateToChallengeLoading
        )
    }

    composable<ChallengeLoading> {
        ChallengeLoadingRoute(
            navigateToChallengeMission = navigateToChallengeMission,
            paddingValues = paddingValues,
            navigateUp = navigateUp
        )
    }

    composable<ChallengeMission> {
        ChallengeMissionSelectedRoute(
            paddingValues = paddingValues,
            navigateToChallengeMissionProgress = navigateToChallengeMissionProgress,
            navigateToChallengeRoutine = navigateToChallengeRoutine
        )
    }

    composable<ChallengeProgress> {
        ChallengeMissionProgressRoute(
            paddingValues = paddingValues,
            onNavigateToChallengeStart = navigateToChallengeStart
        )
    }
}
