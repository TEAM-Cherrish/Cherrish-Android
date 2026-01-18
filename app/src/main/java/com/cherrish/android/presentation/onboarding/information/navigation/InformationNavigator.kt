package com.cherrish.android.presentation.onboarding.information.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.cherrish.android.core.common.navigation.Route
import com.cherrish.android.presentation.onboarding.information.InformationRoute
import kotlinx.serialization.Serializable

@Serializable
data object OnboardingInformation: Route

fun NavController.navigateToOnboardingInformation(navOptions: NavOptions? = null){
    navigate(route = OnboardingInformation, navOptions = navOptions)
}

fun NavGraphBuilder.onboardingInformationNavGraph(
    paddingValues: PaddingValues,
    navigateToHome: () -> Unit
){
    composable<OnboardingInformation> {
        InformationRoute(
            paddingValues = paddingValues,
            navigateToHome = navigateToHome
        )
    }
}
