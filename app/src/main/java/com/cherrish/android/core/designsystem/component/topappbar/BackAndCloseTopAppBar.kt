package com.cherrish.android.core.designsystem.component.topappbar

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cherrish.android.R
import com.cherrish.android.core.common.extension.noRippleClickable
import com.cherrish.android.core.designsystem.theme.CherrishTheme

@Composable
fun BackAndCloseTopAppBar(
    modifier: Modifier = Modifier,
    title: String?,
    onBackClicked: () -> Unit,
    onCloseClicked: () -> Unit
) {
    CherrishBasicTopAppBar(
        modifier = modifier,
        title = title,
        navigationIcon = {
            Icon(
                modifier = Modifier
                    .size(24.dp)
                    .noRippleClickable(onClick = onBackClicked),
                imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_left_24),
                contentDescription = null,
                tint = CherrishTheme.colors.gray1000
            )
        },
        actions = {
            Icon(
                modifier = Modifier
                    .size(24.dp)
                    .noRippleClickable(onClick = onCloseClicked),
                imageVector = ImageVector.vectorResource(R.drawable.ic_close_24),
                contentDescription = null,
                tint = CherrishTheme.colors.gray1000
            )
        }
    )
}

@Preview
@Composable
private fun BackAndCloseTopAppBarPreview() {
    CherrishTheme {
        BackAndCloseTopAppBar(
            title = "시술 여부 선택",
            onBackClicked = {},
            onCloseClicked = {}
        )
    }
}
