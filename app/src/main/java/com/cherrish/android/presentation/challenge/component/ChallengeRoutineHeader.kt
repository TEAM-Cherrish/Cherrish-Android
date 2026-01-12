package com.cherrish.android.presentation.challenge.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cherrish.android.core.designsystem.component.topappbar.BackAndCloseTopAppBar

@Composable
fun ChallengeRoutineHeader(){
    BackAndCloseTopAppBar(
        title = "루틴 챌린지 선택",
        onBackClick= {},
        onCloseClick=  {},
        modifier= Modifier

    )
}

@Preview
@Composable
private fun ChallengeRoutineHeaderPreview(){
    ChallengeRoutineHeader()

}
