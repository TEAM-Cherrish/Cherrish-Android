package com.cherrish.android.presentation.splash.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.cherrish.android.core.common.navigation.Route
import com.cherrish.android.presentation.splash.SplashRoute
import kotlinx.serialization.Serializable

@Serializable
data object Splash : Route


fun NavController.navigateToSplash(navOptions: NavOptions? = null){
    navigate(route = Splash, navOptions = navOptions)
}

fun NavGraphBuilder.splashNavGraph(
    navigateToOnboarding: () -> Unit,
    paddingValues: PaddingValues
){
    composable<Splash> {
        SplashRoute(
            navigateToOnboarding = navigateToOnboarding,
            paddingValues = paddingValues
        )
    }
}
