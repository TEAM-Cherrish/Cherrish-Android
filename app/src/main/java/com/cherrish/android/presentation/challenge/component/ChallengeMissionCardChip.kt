package com.cherrish.android.presentation.challenge.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cherrish.android.R
import com.cherrish.android.core.common.extension.noRippleClickable
import com.cherrish.android.core.designsystem.theme.CherrishTheme
@Composable
fun ChallengeMissionCardChip(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(size = 10.dp))
            .border(
                width = 1.dp,
                color = if (isSelected) CherrishTheme.colors.red500 else CherrishTheme.colors.gray500,
                shape = RoundedCornerShape(size = 10.dp)
            )
            .background(
             color = if (isSelected) CherrishTheme.colors.red100 else CherrishTheme.colors.gray0
            )
            .padding(top= 14.dp, start = 14.dp, bottom = 14.dp, end = 44.dp)
            .noRippleClickable(onClick = onClick),

        horizontalArrangement = Arrangement.spacedBy(space = 6.dp),

        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            imageVector = ImageVector.vectorResource(
                id = if (isSelected) R.drawable.ic_radiobtn_selected else R.drawable.ic_radiobtn_default
            ),
            tint = if (isSelected) CherrishTheme.colors.red700 else CherrishTheme.colors.gray500,
            contentDescription = null,
        )

        Text(
            text = text,
            style = CherrishTheme.typography.body1R14,
            color = if (isSelected) CherrishTheme.colors.gray800 else CherrishTheme.colors.gray700,
            modifier = Modifier.fillMaxWidth()
        )
    }

}

@Preview(showBackground = true)
@Composable
private fun ChallengeMissionCardChipPreview() {
    CherrishTheme {
        var selected by remember { mutableStateOf(value = false) }

        ChallengeMissionCardChip(
            text = "선크림 3번 바르기",
            isSelected = selected,
            onClick = { selected = !selected }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ChallengeMissionCardChipsPreview() {
    CherrishTheme {
        val dummyMissions = listOf(
            "아침 세안 후 토너 바르기",
            "수분 에센스 2-3방울 흡수",
            "보습 크림으로 마무리",
            "저녁 클렌징 꼼꼼히 하기",
            "수분 마스크팩 (주 2-3회)"
        )

        var selectedIndex by remember { mutableStateOf<Int?>(value = null) }

        LazyColumn(
            modifier = Modifier.padding(all =16.dp),
            verticalArrangement = Arrangement.spacedBy(space = 8.dp)
        ) {
            itemsIndexed(items = dummyMissions) { index, text ->
                ChallengeMissionCardChip(
                    text = text,
                    isSelected = selectedIndex == index,
                    onClick = { selectedIndex = index }
                )
            }
        }
    }
}
