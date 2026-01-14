package com.cherrish.android.presentation.mypage.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cherrish.android.R
import com.cherrish.android.core.designsystem.theme.CherrishTheme

@Composable
fun MyPageHeader(
    @DrawableRes profileIcon: Int,
    nicknameText: String,
    skinCareDay: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = profileIcon),
            contentDescription = null,
            modifier = Modifier.clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(width = 14.dp))

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "안녕하세요, $nicknameText 님",
                color = CherrishTheme.colors.gray1000,
                style = CherrishTheme.typography.title1SB18
            )

            Text(
                text = "관리 시작 D + $skinCareDay",
                color = CherrishTheme.colors.gray800,
                style = CherrishTheme.typography.body1M14
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MyPageHeaderPreview() {
    MyPageHeader(
        profileIcon = R.drawable.ic_launcher_foreground,
        nicknameText = "김체체",
        skinCareDay = 13
    )
}
