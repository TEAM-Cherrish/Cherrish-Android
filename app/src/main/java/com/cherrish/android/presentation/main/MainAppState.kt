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
import com.cherrish.android.presentation.calendar.navigation.navigateToCalendar
import com.cherrish.android.presentation.calendar.navigation.navigateToProcedure
import com.cherrish.android.presentation.challenge.navigation.navigateToChallengeMission
import com.cherrish.android.presentation.challenge.navigation.navigateToChallengeRoutine
import com.cherrish.android.presentation.challenge.navigation.navigateToChallengeStart
import com.cherrish.android.presentation.home.navigation.navigateToHome
import com.cherrish.android.presentation.mypage.navigation.navigateToMyPage
import com.cherrish.android.presentation.onboarding.navigation.navigateToOnboarding
import com.cherrish.android.presentation.onboarding.navigation.navigateToOnboardingInformation
import com.cherrish.android.presentation.splash.navigation.Splash
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@Stable
class MainAppState(
    val navController: NavHostController,
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

    private val currentDestination = navController.currentBackStackEntryFlow
        .map { it.destination }
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null
        )

    val currentTab: StateFlow<MainTab?> = currentDestination
        .map { destination ->
            MainTab.find { tab ->
                destination?.hasRoute(tab.route::class) == true
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
            }
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
            MainTab.CHALLENGE -> navController.navigateToChallengeStart(navOptions = navOptions)
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

    fun navigateUp() {
        navController.navigateUp()
    }

    fun navigateToProcedure() {
        navController.navigateToProcedure()
    }

    fun navigateToChallengeRoutine() {
        navController.navigateToChallengeRoutine()
    }
    fun navigateToChallengeMission(navOptions: NavOptions? = clearStackNavOptions) {
        navController.navigateToChallengeMission(navOptions)
    }
}

@Composable
fun rememberMainAppState(
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
): MainAppState = remember(navController, coroutineScope) {
    MainAppState(
        navController = navController,
        coroutineScope = coroutineScope
    )
}
