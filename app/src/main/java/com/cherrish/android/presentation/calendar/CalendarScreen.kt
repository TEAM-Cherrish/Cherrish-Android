package com.cherrish.android.presentation.calendar

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CalendarRoute(
    paddingValues: PaddingValues
) {
    CalendarScreen(paddingValues = paddingValues)
}

@Composable
private fun CalendarScreen(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier
) {
    Text(
        "Calendar",
        modifier = modifier.padding(paddingValues)
    )
}
