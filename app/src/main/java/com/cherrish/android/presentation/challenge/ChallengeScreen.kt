package com.cherrish.android.presentation.challenge

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ChallengeRoute(
    paddingValues: PaddingValues
) {
    ChallengeScreen(paddingValues = paddingValues)
}

@Composable
private fun ChallengeScreen(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier
) {
    Text(
        "Challenge",
        modifier = modifier.padding(paddingValues)
    )
}
