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

data object Calendar : MainTabRoute

@Serializable
private data object Procedure : Route

fun NavController.navigateToCalendar(
    navOptions: NavOptions? = null
) {
    navigate(
        route = Calendar,
        navOptions = navOptions
    )
}

fun NavController.navigateToProcedure(
    navOptions: NavOptions? = null
) {
    navigate(route = Procedure, navOptions = navOptions)
}

fun NavGraphBuilder.calendarNavGraph(
    paddingValues: PaddingValues,
    navigateUp: () -> Unit,
    navigateToProcedure: () -> Unit
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
