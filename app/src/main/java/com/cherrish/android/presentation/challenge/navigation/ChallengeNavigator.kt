package com.cherrish.android.presentation.challenge.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.compose.composable
import com.cherrish.android.core.common.navigation.MainTabRoute
import com.cherrish.android.core.common.navigation.Route
import com.cherrish.android.presentation.challenge.mission.ChallengeMissionSelectedRoute
import com.cherrish.android.presentation.challenge.routine.ChallengeRoutineRoute
import com.cherrish.android.presentation.challenge.start.ChallengeStartRoute
import kotlinx.serialization.Serializable

@Serializable
data object ChallengeStart : MainTabRoute

@Serializable
data object ChallengeRoutine : Route

@Serializable
data object ChallengeMission : Route
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

fun  NavController.navigateToChallengeMission(
    navOptions : NavOptions? = null
){
    navigate(
        route = ChallengeMission,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.challengeNavGraph(
    paddingValues: PaddingValues,
    navigateToChallengeRoutine: () -> Unit,
    navigationChallengeMission: ()-> Unit,
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
            onBackClick = navigateUp,
            onCloseClick = navigateUp,
            navigateToMission = navigationChallengeMission
        )
    }

    composable<ChallengeMission> {
        ChallengeMissionSelectedRoute(
            paddingValues = paddingValues,
            navigateToProgress = {  }
        )
    }
}
