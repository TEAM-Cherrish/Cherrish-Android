package com.cherrish.android.presentation.home.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.cherrish.android.core.common.navigation.MainTabRoute
import com.cherrish.android.presentation.home.HomeRoute
import kotlinx.serialization.Serializable

@Serializable
data object Home : MainTabRoute

fun NavController.navigateToHome(
    navOptions: NavOptions? = null
) {
    navigate(
        route = Home,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.homeNavGraph(
    paddingValues: PaddingValues
) {
    composable<Home> {
        HomeRoute(
            paddingValues = paddingValues,
            navigateToChallenge = {}
        )
    }
}
