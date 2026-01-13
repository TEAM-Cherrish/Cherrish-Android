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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cherrish.android.core.designsystem.component.chip.CherrishMissionCard
import com.cherrish.android.core.designsystem.component.chip.CherrishSelectionChip
import com.cherrish.android.core.designsystem.component.type.CherrishSectionChipType
import com.cherrish.android.core.designsystem.theme.CherrishTheme
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun CherrishSelectionSection(
    title: String,
    descriptionTextStyle: TextStyle,
    items: ImmutableList<String>,
    chipType: CherrishSectionChipType,
    onItemClick: (index: Int) -> Unit,
    modifier: Modifier = Modifier,
    description: String? = null,
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

        SelectionChipGrid(
            items = items,
            selectedIndex = selectedIndex,
            onItemClick = onItemClick,
            chipType = chipType
        )
    }
}

@Composable
private fun SelectionChipGrid(
    items: ImmutableList<String>,
    chipType: CherrishSectionChipType,
    onItemClick: (index: Int) -> Unit,
    modifier: Modifier = Modifier,
    selectedIndex: Int? = null
) {
    LazyVerticalGrid(
        modifier = modifier.fillMaxWidth(),
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        itemsIndexed(
            items = items,
            key = { index, item -> "$item-$index" }
        ) { index, text ->
            when (chipType) {
                CherrishSectionChipType.SELECTION_CHIP -> {
                    CherrishSelectionChip(
                        text = text,
                        onClick = { onItemClick(index) },
                        modifier = Modifier.fillMaxWidth(),
                        isSelected = selectedIndex == index
                    )
                }

                CherrishSectionChipType.MISSION_CARD -> {
                    CherrishMissionCard(
                        text = text,
                        onClick = { onItemClick(index) },
                        modifier = Modifier.fillMaxWidth(),
                        isSelected = selectedIndex == index
                    )
                }
            }
        }
    }
}

@Composable
private fun TitleDescriptionSection(
    title: String,
    descriptionTextStyle: TextStyle,
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
                style = descriptionTextStyle,
                color = CherrishTheme.colors.gray700
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SelectionSectionSelectionChipPreview() {
    CherrishTheme {
        var isSelected by remember { mutableIntStateOf(-1) }

        CherrishSelectionSection(
            title = "요즘 가장 신경 쓰이는\n피부 고민은 무엇인가요?",
            description = "선택한 고민을 기준으로 시술 정보를 정리해줘요.",
            descriptionTextStyle = CherrishTheme.typography.body1R14,
            items = persistentListOf("여드름 ∙ 트러블", "진정 토너+세럼", "피부결 정돈"),
            selectedIndex = isSelected,
            onItemClick = { isSelected = it },
            chipType = CherrishSectionChipType.SELECTION_CHIP
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SelectionSectionMissionCardPreview() {
    CherrishTheme {
        var isSelected by remember { mutableIntStateOf(-1) }

        CherrishSelectionSection(
            title = "챌린지 기간 동안\n진행할 미션을 선택해주세요.",
            description = "복수 선택이 가능해요.",
            descriptionTextStyle = CherrishTheme.typography.body1M14,
            items = persistentListOf("반신욕 20분", "진정 토너+세럼", "피부결 정돈", "괄사", "톤 개선", "선크림 바르기"),
            selectedIndex = isSelected,
            onItemClick = { isSelected = it },
            chipType = CherrishSectionChipType.MISSION_CARD
        )
    }
}
