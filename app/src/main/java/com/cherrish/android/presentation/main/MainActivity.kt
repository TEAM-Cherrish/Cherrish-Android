package com.cherrish.android.presentation.main

import android.content.pm.ActivityInfo
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.cherrish.android.core.designsystem.theme.CherrishTheme
import com.cherrish.android.data.repository.ChallengeMissionProgressRepository
import com.cherrish.android.presentation.calendar.CalendarRefreshEventBus
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var calendarEventBus: CalendarRefreshEventBus

    @Inject
    lateinit var challengeMissionProgressRepository: ChallengeMissionProgressRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.dark(Color.TRANSPARENT)
        )
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        setContent {
            CherrishTheme {
                val appState = rememberMainAppState(calendarEventBus = calendarEventBus)
                MainScreen(
                    appState = appState,
                    challengeMissionProgressRepository = challengeMissionProgressRepository
                )
            }
        }
    }
}
