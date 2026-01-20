package com.cherrish.android.presentation.calendar.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.cherrish.android.core.common.navigation.MainTabRoute
import com.cherrish.android.core.common.navigation.Route
import com.cherrish.android.presentation.calendar.CalendarRoute
import com.cherrish.android.presentation.calendar.procedure.ProcedureRoute
import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
data object Calendar : MainTabRoute

@Serializable
data class Procedure(val startDate: String) : Route

fun NavController.navigateToCalendar(
    navOptions: NavOptions? = null
) {
    navigate(
        route = Calendar,
        navOptions = navOptions
    )
}

fun NavController.navigateToProcedure(
    startDate: LocalDate,
    navOptions: NavOptions? = null
) {
    navigate(route = Procedure(startDate = startDate.toString()), navOptions = navOptions)
}

fun NavGraphBuilder.calendarNavGraph(
    paddingValues: PaddingValues,
    navigateUp: () -> Unit,
    navigateToProcedure: (LocalDate) -> Unit
) {
    composable<Calendar> {
        CalendarRoute(
            paddingValues = paddingValues,
            onNavigateToProcedure = navigateToProcedure
        )
    }

    composable<Procedure> {
        ProcedureRoute(
            onNavigateBack = navigateUp,
            onComplete = navigateUp
        )
    }
}
