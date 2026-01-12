package com.cherrish.android.presentation.challenge.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.cherrish.android.core.designsystem.theme.CherrishTheme

@Composable
fun ChallengeRoutineOnboardingBody(modifier: Modifier= Modifier){
    Text(text ="지금 나에게 가장 필요한 관리 루틴을 선택하세요", modifier = Modifier, color = CherrishTheme.colors.gray1000,style = CherrishTheme.typography.title1SB18)
    Text(text = "관리 루틴을 선택해주세요", modifier = Modifier, color = CherrishTheme.colors.gray1000, style = CherrishTheme.typography.title1SB18)
}
