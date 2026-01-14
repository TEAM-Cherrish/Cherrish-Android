package com.cherrish.android.presentation.mypage

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
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
    Column(modifier = modifier.padding(paddingValues)) {
        MyPageHeader(
            profileIcon = R.drawable.ic_launcher_foreground,
            nicknameText = uiState.nicknameText,
            skinCareDay = uiState.skinCareDay,
            modifier = Modifier.fillMaxWidth()
        )

        HorizontalDivider(
            color = CherrishTheme.colors.gray100,
            modifier = Modifier.height(height = 10.dp)
        )

        Box(
            modifier = Modifier
                .weight(weight = 1f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null,
                modifier = Modifier.padding(horizontal = 52.dp)
            )
        }

        HorizontalDivider(
            color = CherrishTheme.colors.gray100,
            modifier = Modifier.height(height = 10.dp)
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
