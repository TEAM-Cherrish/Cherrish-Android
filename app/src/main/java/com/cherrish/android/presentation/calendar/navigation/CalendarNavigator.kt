package com.cherrish.android.presentation.calendar.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.cherrish.android.core.common.navigation.MainTabRoute
import com.cherrish.android.core.common.navigation.Route
import com.cherrish.android.presentation.calendar.CalendarRoute
import com.cherrish.android.presentation.calendar.procedure.ProcedureRoute
import java.time.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data object Calendar : MainTabRoute

@Serializable
data class Procedure(val startDate: String) : Route

private const val CALENDAR_REFRESH_KEY = "calendar_refresh"

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
    navController: NavController,
    navigateUp: () -> Unit,
    navigateToProcedure: (LocalDate) -> Unit
) {
    composable<Calendar> { backStackEntry ->
        val refreshRequested by backStackEntry.savedStateHandle
            .getStateFlow(CALENDAR_REFRESH_KEY, false)
            .collectAsStateWithLifecycle()

        CalendarRoute(
            paddingValues = paddingValues,
            onNavigateToProcedure = navigateToProcedure,
            shouldRefresh = refreshRequested,
            onRefreshConsumed = {
                backStackEntry.savedStateHandle[CALENDAR_REFRESH_KEY] = false
            }
        )
    }

    composable<Procedure> {
        ProcedureRoute(
            onNavigateBack = navigateUp,
            onComplete = {
                navController.previousBackStackEntry
                    ?.savedStateHandle
                    ?.set(CALENDAR_REFRESH_KEY, true)
                navigateUp()
            }
        )
    }
}
