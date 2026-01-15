package com.cherrish.android.presentation.mypage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cherrish.android.R
import com.cherrish.android.core.common.state.UiState
import com.cherrish.android.core.designsystem.theme.CherrishTheme
import com.cherrish.android.presentation.mypage.component.MyPageHeader

@Composable
fun MyPageRoute(
    paddingValues: PaddingValues,
    viewModel: MyPageViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (val state = uiState) {
        is UiState.Loading -> {
        }

        is UiState.Failure -> {
        }

        is UiState.Success -> {
            MyPageScreen(
                paddingValues = paddingValues,
                uiState = state.data
            )
        }

        else -> {}
    }
}

@Composable
private fun MyPageScreen(
    paddingValues: PaddingValues,
    uiState: MyPageUiState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(top = 44.dp)
            .background(color = CherrishTheme.colors.gray0),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        MyPageHeader(
            profileIcon = R.drawable.img_mypage_profile,
            nicknameText = uiState.nicknameText,
            skinCareDay = uiState.skinCareDay,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        HorizontalDivider(
            color = CherrishTheme.colors.gray100,
            thickness = 10.dp
        )

        Spacer(Modifier.weight(weight = 1f))

        Image(
            painter = painterResource(id = R.drawable.img_mypage_preparing),
            contentDescription = null,
            modifier = Modifier.padding(horizontal = 26.dp)

        )

        Spacer(Modifier.weight(weight = 1f))

        HorizontalDivider(
            color = CherrishTheme.colors.gray100,
            thickness = 10.dp
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFF6F8)
@Composable
private fun MyPageScreenPreview() {
    MyPageScreen(
        paddingValues = PaddingValues(),
        uiState = MyPageUiState.FakeUsers
    )
}
