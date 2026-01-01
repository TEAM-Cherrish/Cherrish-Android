package com.cherrish.android.presentation.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HomeRoute(
    paddingValues: PaddingValues
) {
    HomeScreen(paddingValues = paddingValues)
}

@Composable
private fun HomeScreen(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier
) {
    Text(
        text = "Home",
        modifier = modifier.padding(paddingValues)
    )
}
