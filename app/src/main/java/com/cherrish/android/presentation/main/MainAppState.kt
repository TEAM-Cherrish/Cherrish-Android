package com.cherrish.android.presentation.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.cherrish.android.presentation.calendar.CalendarRefreshEventBus
import com.cherrish.android.presentation.calendar.navigation.navigateToCalendar
import com.cherrish.android.presentation.calendar.navigation.navigateToProcedure
import com.cherrish.android.presentation.challenge.navigation.ChallengeProgress
import com.cherrish.android.presentation.challenge.navigation.navigateToChallengeLoading
import com.cherrish.android.presentation.challenge.navigation.navigateToChallengeMission
import com.cherrish.android.presentation.challenge.navigation.navigateToChallengeMissionProgress
import com.cherrish.android.presentation.challenge.navigation.navigateToChallengeRoutine
import com.cherrish.android.presentation.challenge.navigation.navigateToChallengeStart
import com.cherrish.android.presentation.home.navigation.navigateToHome
import com.cherrish.android.presentation.mypage.navigation.navigateToMyPage
import com.cherrish.android.presentation.onboarding.navigation.navigateToOnboarding
import com.cherrish.android.presentation.onboarding.navigation.navigateToOnboardingInformation
import com.cherrish.android.presentation.splash.navigation.Splash
import java.time.LocalDate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@Stable
class MainAppState(
    val navController: NavHostController,
    val calendarEventBus: CalendarRefreshEventBus,
    coroutineScope: CoroutineScope
) {
    val startDestination = Splash

    private val clearStackNavOptions = navOptions {
        popUpTo(0) { inclusive = true }
        launchSingleTop = true
        restoreState = false
    }

    private val keepStackNavOptions = navOptions {
        launchSingleTop = true
        restoreState = true
    }

    private fun challengeLoadingStackNavOptions(): NavOptions =
        navOptions {
            popUpTo(
                navController.currentDestination?.route
                    ?: return@navOptions
            ) {
                inclusive = true
                saveState = false
            }
        }

    private val currentDestination = navController.currentBackStackEntryFlow
        .map { it.destination }
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null
        )

    val currentTab: StateFlow<MainTab?> = currentDestination
        .map { destination ->
            if (destination?.hasRoute(ChallengeProgress::class) == true) {
                MainTab.CHALLENGE
            } else {
                MainTab.find { tab ->
                    destination?.hasRoute(tab.route::class) == true
                }
            }
        }
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null
        )

    val isBottomBarVisible: StateFlow<Boolean> = currentDestination
        .map { destination ->
            MainTab.contains { tab ->
                destination?.hasRoute(tab.route::class) == true
            } || destination?.hasRoute(ChallengeProgress::class) == true
        }
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false
        )

    fun navigate(tab: MainTab) {
        val navOptions = navOptions {
            navController.currentDestination?.route?.let {
                popUpTo(it) {
                    inclusive = true
                    saveState = true
                }
                restoreState = true
                launchSingleTop = true
            }
        }

        when (tab) {
            MainTab.HOME -> navController.navigateToHome(navOptions = navOptions)
            MainTab.CALENDAR -> navController.navigateToCalendar(navOptions = navOptions)
            MainTab.MYPAGE -> navController.navigateToMyPage(navOptions = navOptions)
            MainTab.CHALLENGE -> {}
        }
    }

    fun navigateToChallengeTab(hasChallengeRegistered: Boolean) {
        val navOptions = navOptions {
            navController.currentDestination?.route?.let {
                popUpTo(it) {
                    inclusive = true
                    saveState = true
                }
                restoreState = true
                launchSingleTop = true
            }
        }

        if (hasChallengeRegistered) {
            navController.navigateToChallengeMissionProgress(navOptions = navOptions)
        } else {
            navController.navigateToChallengeStart(navOptions = navOptions)
        }
    }

    fun navigateToOnboarding(navOptions: NavOptions? = clearStackNavOptions) {
        navController.navigateToOnboarding(navOptions)
    }

    fun navigateToOnboardingInformation(navOptions: NavOptions? = clearStackNavOptions) {
        navController.navigateToOnboardingInformation(navOptions)
    }

    fun navigateToHome(navOptions: NavOptions? = clearStackNavOptions) {
        navController.navigateToHome(navOptions)
    }

    fun navigateToProcedure(startDate: LocalDate) {
        navController.navigateToProcedure(startDate)
    }

    fun navigateUp() {
        navController.navigateUp()
    }

    fun navigateToChallengeRoutine() {
        navController.navigateToChallengeRoutine()
    }

    fun navigateToChallengeLoading(
        routineId: Int,
        routineName: String,
        navOptions: NavOptions? = keepStackNavOptions
    ) {
        navController.navigateToChallengeLoading(
            routineId = routineId,
            routineName = routineName,
            navOptions = navOptions
        )
    }

    fun navigateToChallengeMission(
        routineId: Int,
        routines: List<String>
    ) {
        navController.navigateToChallengeMission(
            routineId = routineId,
            routines = routines,
            navOptions = challengeLoadingStackNavOptions()
        )
    }

    fun navigateToChallengeStart(navOptions: NavOptions? = clearStackNavOptions) {
        navController.navigateToChallengeStart(navOptions = navOptions)
    }

    fun navigateToChallengeMissionProgress(
        navOptions: NavOptions? = clearStackNavOptions
    ) {
        navController.navigateToChallengeMissionProgress(navOptions = navOptions)
    }

    fun navigateToCalendarSelected(date: LocalDate) {
        navController.navigateToCalendar(date = date)
    }
}

@Composable
fun rememberMainAppState(
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    calendarEventBus: CalendarRefreshEventBus
): MainAppState = remember(navController, coroutineScope, calendarEventBus) {
    MainAppState(
        navController = navController,
        calendarEventBus = calendarEventBus,
        coroutineScope = coroutineScope
    )
}
