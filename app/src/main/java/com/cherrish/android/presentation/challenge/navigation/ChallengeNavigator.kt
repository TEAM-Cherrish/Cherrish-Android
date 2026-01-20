package com.cherrish.android.presentation.challenge.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.cherrish.android.core.common.navigation.MainTabRoute
import com.cherrish.android.presentation.challenge.start.ChallengeStartRoute
import kotlinx.serialization.Serializable

@Serializable
data object ChallengeStart : MainTabRoute

fun NavController.navigateToChallengeStart(
    navOptions: NavOptions? = null
) {
    navigate(
        route = ChallengeStart,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.challengeNavGraph(
    paddingValues: PaddingValues
) {
    composable<ChallengeStart> {
        ChallengeStartRoute(
            paddingValues = paddingValues
        )
    }
}
