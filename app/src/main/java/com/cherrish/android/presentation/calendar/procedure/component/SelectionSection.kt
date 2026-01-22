package com.cherrish.android.presentation.calendar.procedure.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cherrish.android.core.designsystem.component.chip.CherrishSelectionChip
import com.cherrish.android.core.designsystem.theme.CherrishTheme
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun SelectionSection(
    title: String,
    items: ImmutableList<String>,
    onItemClick: (index: Int) -> Unit,
    modifier: Modifier = Modifier,
    description: String? = null,
    descriptionTextStyle: TextStyle? = null,
    selectedIndex: Int? = null
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        TitleDescriptionSection(
            title = title,
            descriptionTextStyle = descriptionTextStyle,
            description = description
        )

        Spacer(modifier = Modifier.height(40.dp))

        ChipFlowGrid(
            items = items,
            selectedIndex = selectedIndex,
            onItemClick = onItemClick
        )
    }
}

@Composable
private fun ChipFlowGrid(
    items: ImmutableList<String>,
    onItemClick: (index: Int) -> Unit,
    modifier: Modifier = Modifier,
    selectedIndex: Int? = null
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items.chunked(2).forEachIndexed { rowIndex, rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                rowItems.forEachIndexed { colIndex, text ->
                    val index = rowIndex * 2 + colIndex
                    CherrishSelectionChip(
                        text = text,
                        onClick = { onItemClick(index) },
                        modifier = Modifier.weight(1f),
                        isSelected = selectedIndex == index
                    )
                }
                if (rowItems.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
private fun TitleDescriptionSection(
    title: String,
    modifier: Modifier = Modifier,
    description: String? = null,
    descriptionTextStyle: TextStyle? = null
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
                style = descriptionTextStyle ?: CherrishTheme.typography.body1R14,
                color = CherrishTheme.colors.gray700
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SelectionSectionWithDescriptionPreview() {
    CherrishTheme {
        var isSelected by remember { mutableIntStateOf(-1) }

        SelectionSection(
            title = "요즘 가장 신경 쓰이는\n피부 고민은 무엇인가요?",
            description = "선택한 고민을 기준으로 시술 정보를 정리해줘요.",
            descriptionTextStyle = CherrishTheme.typography.body1R14,
            items = persistentListOf("여드름 ∙ 트러블", "진정 토너+세럼", "피부결 정돈"),
            selectedIndex = isSelected,
            onItemClick = { isSelected = it }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SelectionSectionPreview() {
    CherrishTheme {
        var isSelected by remember { mutableIntStateOf(-1) }

        SelectionSection(
            title = "요즘 가장 신경 쓰이는\n피부 고민은 무엇인가요?",
            items = persistentListOf("여드름 ∙ 트러블", "진정 토너+세럼", "피부결 정돈"),
            selectedIndex = isSelected,
            onItemClick = { isSelected = it }
        )
    }
}
