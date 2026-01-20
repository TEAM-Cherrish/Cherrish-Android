package com.cherrish.android.presentation.calendar.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.cherrish.android.core.common.navigation.MainTabRoute
import com.cherrish.android.presentation.calendar.CalendarRoute
import com.cherrish.android.presentation.calendar.procedure.ProcedureRoute
import kotlinx.serialization.Contextual
import java.time.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data object Calendar : MainTabRoute

@Serializable
data class Procedure(@Contextual val startDate: LocalDate)

fun NavController.navigateToCalendar(navOptions: NavOptions? = null) =
    navigate(Calendar, navOptions)

fun NavController.navigateToProcedure(startDate: LocalDate, navOptions: NavOptions? = null) =
    navigate(Procedure(startDate = startDate), navOptions)

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
