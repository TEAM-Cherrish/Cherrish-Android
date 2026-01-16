package com.cherrish.android.presentation.calendar.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.cherrish.android.core.common.navigation.MainTabRoute
import com.cherrish.android.presentation.calendar.CalendarRoute
import com.cherrish.android.presentation.calendar.procedure.ProcedureRoute
import kotlinx.serialization.Serializable

@Serializable
data object Calendar : MainTabRoute

@Serializable
private data object Procedure

fun NavController.navigateToCalendar(
    navOptions: NavOptions? = null
) {
    navigate(
        route = Calendar,
        navOptions = navOptions
    )
}

fun NavController.navigateToProcedure() {
    navigate(route = Procedure)
}

fun NavGraphBuilder.calendarNavGraph(
    paddingValues: PaddingValues,
    navController: NavController
) {
    composable<Calendar> {
        CalendarRoute(
            paddingValues = paddingValues,
            onNavigateToProcedure = navController::navigateToProcedure
        )
    }

    composable<Procedure> {
        ProcedureRoute(
            onNavigateBack = navController::popBackStack,
            onComplete = navController::popBackStack
        )
    }
}
