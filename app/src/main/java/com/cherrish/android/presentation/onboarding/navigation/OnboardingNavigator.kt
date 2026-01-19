package com.cherrish.android.presentation.onboarding.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.cherrish.android.core.common.navigation.Route
import com.cherrish.android.presentation.onboarding.OnboardingRoute
import kotlinx.serialization.Serializable

@Serializable
data object Onboarding : Route

fun NavController.navigateToOnboarding(navOptions: NavOptions? = null) {
    navigate(route = Onboarding, navOptions = navOptions)
}

fun NavGraphBuilder.onboardingNavGraph(
    paddingValues: PaddingValues,
    navigateToOnboardingInformation: () -> Unit
) {
    composable<Onboarding> {
        OnboardingRoute(
            paddingValues = paddingValues,
            navigateToOnboardingInformation = navigateToOnboardingInformation
        )
    }
}
