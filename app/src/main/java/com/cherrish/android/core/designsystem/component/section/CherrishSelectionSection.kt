package com.cherrish.android.core.designsystem.component.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cherrish.android.core.designsystem.component.chip.CherrishSelectionChip
import com.cherrish.android.core.designsystem.theme.CherrishTheme

@Composable
fun CherrishSelectionSection(
    title: String,
    items: List<String>,
    modifier: Modifier = Modifier,
    description: String? = null,
    selectedIndex: Int? = null,
    onItemClick: (index: Int) -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        TitleDescriptionSection(
            title = title,
            description = description
        )

        Spacer(modifier = Modifier.height(40.dp))

        SelectionChipGrid(
            items = items,
            selectedIndex = selectedIndex,
            onItemClick = onItemClick
        )
    }
}

@Composable
private fun SelectionChipGrid(
    items: List<String>,
    modifier: Modifier = Modifier,
    selectedIndex: Int? = null,
    onItemClick: (index: Int) -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier.fillMaxWidth(),
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        userScrollEnabled = false
    ) {
        itemsIndexed(items) { index, text ->
            CherrishSelectionChip(
                text = text,
                onClick = { onItemClick(index) },
                modifier = Modifier.fillMaxWidth(),
                isSelected = selectedIndex == index
            )
        }
    }
}

@Composable
private fun TitleDescriptionSection(
    title: String,
    modifier: Modifier = Modifier,
    description: String? = null
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = title,
            style = CherrishTheme.typography.title1SB18,
            color = CherrishTheme.colors.gray1000
        )

        if (!description.isNullOrBlank()) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = description,
                style = CherrishTheme.typography.body1R14,
                color = CherrishTheme.colors.gray700
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CherrishSelectionSectionPreview_TwoOptions() {
    CherrishTheme {
        var isSelected by remember { mutableIntStateOf(-1) }

        CherrishSelectionSection(
            title = "시술 일정을 추가해볼게요.\n이미 생각해둔 시술이 있나요?",
            description = "시술을 선택하셨는지 확인할게요.",
            items = listOf("선택한 시술이 있어요", "아직 선택 전이에요"),
            selectedIndex = isSelected,
            onItemClick = { isSelected = it }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CherrishSelectionSectionPreview_SixOptions() {
    CherrishTheme {
        var isSelected by remember { mutableIntStateOf(-1) }

        CherrishSelectionSection(
            title = "요즘 가장 신경 쓰이는\n피부 고민은 무엇인가요?",
            description = "선택한 고민 기준으로 시술 정보를 정리해드려요.",
            items = listOf("피부결·각질", "색소·잡티", "홍조", "탄력·주름", "모공", "트러블"),
            selectedIndex = isSelected,
            onItemClick = { isSelected = it }
        )
    }
}
