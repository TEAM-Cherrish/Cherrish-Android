package com.cherrish.android.presentation.main

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import com.cherrish.android.presentation.calendar.navigation.calendarNavGraph
import com.cherrish.android.presentation.challenge.navigation.challengeNavGraph
import com.cherrish.android.presentation.home.navigation.homeNavGraph
import com.cherrish.android.presentation.main.component.MainBottomBar
import com.cherrish.android.presentation.mypage.navigation.myPageNavGraph
import com.cherrish.android.presentation.onboarding.navigation.onboardingInformationNavGraph
import com.cherrish.android.presentation.onboarding.navigation.onboardingNavGraph
import com.cherrish.android.presentation.splash.navigation.splashNavGraph
import kotlinx.collections.immutable.toPersistentList

@Composable
fun MainScreen(
    appState: MainAppState
) {
    val isBottomBarVisible by appState.isBottomBarVisible.collectAsStateWithLifecycle()
    val currentTab by appState.currentTab.collectAsStateWithLifecycle()


    Scaffold(
        bottomBar = {
            MainBottomBar(
                visible = isBottomBarVisible,
                tabs = MainTab.entries.toPersistentList(),
                currentTab = currentTab,
                onTabSelected = appState::navigate
            )
        }
    ) { innerPadding ->
        NavHost(
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None },
            popEnterTransition = { EnterTransition.None },
            popExitTransition = { ExitTransition.None },
            navController = appState.navController,
            startDestination = appState.startDestination
        ) {
            splashNavGraph(
                navigateToOnboarding = appState::navigateToOnboarding,
                paddingValues = innerPadding
            )

            onboardingNavGraph(
                paddingValues = innerPadding,
                navigateToOnboardingInformation = appState::navigateToOnboardingInformation
            )

            onboardingInformationNavGraph(
                paddingValues = innerPadding,
                navigateToHome = appState::navigateToHome
            )

            homeNavGraph(paddingValues = innerPadding)

            calendarNavGraph(paddingValues = innerPadding)

            challengeNavGraph(paddingValues = innerPadding)

            myPageNavGraph(paddingValues = innerPadding)
        }
    }
}
