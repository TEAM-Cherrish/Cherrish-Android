package com.cherrish.android.presentation.mypage

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MyPageRoute(
    paddingValues: PaddingValues
) {
    MyPageScreen(paddingValues = paddingValues)
}

@Composable
private fun MyPageScreen(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier
) {
    Text(
        text = "MyPage",
        modifier = modifier.padding(paddingValues)
    )
}
