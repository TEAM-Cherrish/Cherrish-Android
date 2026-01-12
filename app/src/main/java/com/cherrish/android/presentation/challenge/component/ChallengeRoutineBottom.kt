package com.cherrish.android.presentation.challenge.component

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cherrish.android.core.designsystem.component.button.CherrishButton

@Composable
fun ChallengeRoutineBottom(modifier: Modifier = Modifier) {
    CherrishButton(
        text = "다음",
        onClick = {},
        modifier = modifier
    )
}

@Preview
@Composable
private fun ChallengeRoutineBottomPreview() {
    ChallengeRoutineBottom(modifier = Modifier.padding(all = 20.20.dp))
}
