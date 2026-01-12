package com.cherrish.android.presentation.challenge

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cherrish.android.core.designsystem.component.button.CherrishButton
import com.cherrish.android.core.designsystem.component.topappbar.CherrishBasicTopAppBar
import com.cherrish.android.presentation.challenge.component.ChallengeRoutineOnboardingBody
import com.cherrish.android.presentation.challenge.model.ChallengeRoutineCategory

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
    var selectedCategory by remember {
        mutableStateOf<ChallengeRoutineCategory?>(value = null)
    }

    Column(modifier = modifier
        .padding(start = 26.dp, bottom = 64.dp, end = 26.dp)
    ) {

        CherrishBasicTopAppBar()

        Spacer(modifier = Modifier.height(height = 70.dp))

        ChallengeRoutineOnboardingBody(
            selectedCategory = selectedCategory,
            onCategoryClick = { selectedCategory = it }
        )

        Spacer(modifier = Modifier.weight(1f))

        CherrishButton(
            text = "다음",
            onClick = {},
        )
    }
}

@Preview
@Composable
private fun ChallengeScreenPreview() {
    ChallengeScreen(paddingValues = PaddingValues(26.dp))
}
