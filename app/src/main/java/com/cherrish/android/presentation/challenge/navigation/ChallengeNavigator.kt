package com.cherrish.android.presentation.challenge.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.cherrish.android.core.common.navigation.MainTabRoute
import com.cherrish.android.presentation.challenge.ChallengeRoute
import kotlinx.serialization.Serializable

@Serializable
data object Challenge : MainTabRoute

fun NavController.navigateToChallenge(
    navOptions: NavOptions? = null
) {
    navigate(
        route = Challenge,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.challengeNavGraph(
    paddingValues: PaddingValues
) {
    composable<Challenge> {
        ChallengeRoute(
            paddingValues = paddingValues
        )
    }
}
