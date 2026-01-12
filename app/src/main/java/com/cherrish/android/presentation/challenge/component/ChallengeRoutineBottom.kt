package com.cherrish.android.presentation.challenge.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cherrish.android.core.designsystem.component.button.CherrishButton

@Composable
fun ChallengeRoutineBottom (modifier :Modifier = Modifier){
    CherrishButton(
        text = "다음",
        onClick = {},
    )
}

@Preview
@Composable
private fun ChallengeRoutineBottomPreview(){
        ChallengeRoutineBottom()
}
