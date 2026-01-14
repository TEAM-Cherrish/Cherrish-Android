package com.cherrish.android.presentation.mypage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cherrish.android.R
import com.cherrish.android.core.designsystem.theme.CherrishTheme
import com.cherrish.android.presentation.mypage.component.MyPageHeader
import com.cherrish.android.presentation.mypage.navigation.MyPage

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
    Column(modifier = modifier.padding(paddingValues)) {

        MyPageHeader(profileIcon = R.drawable.ic_launcher_foreground ,
            nicknameText = "",
            skinCareDay = 0,
            modifier = Modifier
        )
    // 조 ㅁ있다 uiState로 만들기
        HorizontalDivider(
            color = CherrishTheme.colors.gray100,
            modifier = Modifier.height(height = 10.dp)
        )

        Spacer(Modifier.weight(weight = 1f))

        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = null,
            modifier = Modifier,
        )

        Spacer(Modifier.weight( weight = 1f))

        HorizontalDivider(
            color = CherrishTheme.colors.gray100,
            modifier = Modifier.height(height = 10.dp)
        )
    }
}

@Preview(showBackground = true,
    backgroundColor =  0xFFFFF6F8)
@Composable
private fun MyPageScreenPreview(){
    MyPageScreen(paddingValues = PaddingValues())
}
