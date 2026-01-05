package com.cherrish.android.presentation.calendar.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.cherrish.android.core.common.navigation.MainTabRoute
import com.cherrish.android.presentation.calendar.CalendarRoute
import kotlinx.serialization.Serializable

@Serializable
data object Calendar : MainTabRoute

fun NavController.navigateToCalendar(
    navOptions: NavOptions? = null
) {
    navigate(
        route = Calendar,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.calendarNavGraph(
    paddingValues: PaddingValues
) {
    composable<Calendar> {
        CalendarRoute(
            paddingValues = paddingValues
        )
    }
}
