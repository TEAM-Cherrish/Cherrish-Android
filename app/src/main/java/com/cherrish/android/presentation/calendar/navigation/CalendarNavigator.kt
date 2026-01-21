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
data class Calendar(val date: String) : MainTabRoute

@Serializable
data class Procedure(val startDate: String)

fun NavController.navigateToCalendar(date: LocalDate? = LocalDate.now(), navOptions: NavOptions? = null) =
    navigate(Calendar(date = date.toString()), navOptions)

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
            navigateToProcedure = navigateToProcedure
        )
    }

    composable<Procedure> {
        ProcedureRoute(
            onNavigateBack = navigateUp
        )
    }
}
