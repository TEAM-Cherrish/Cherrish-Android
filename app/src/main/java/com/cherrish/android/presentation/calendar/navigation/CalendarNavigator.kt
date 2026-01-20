package com.cherrish.android.presentation.calendar.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.cherrish.android.core.common.navigation.MainTabRoute
import com.cherrish.android.presentation.calendar.CalendarRoute
import com.cherrish.android.presentation.calendar.procedure.ProcedureRoute
import java.time.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data object Calendar : MainTabRoute

@Serializable
data class Procedure(val startDate: String)

fun NavController.navigateToCalendar(navOptions: NavOptions? = null) =
    navigate(Calendar, navOptions)

fun NavController.navigateToProcedure(startDate: LocalDate, navOptions: NavOptions? = null) =
    navigate(Procedure(startDate = startDate.toString()), navOptions)

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
            onNavigateBack = navigateUp
        )
    }
}
