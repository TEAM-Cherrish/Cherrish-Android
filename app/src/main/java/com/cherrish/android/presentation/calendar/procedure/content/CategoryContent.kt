package com.cherrish.android.presentation.calendar.procedure.content

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.cherrish.android.core.designsystem.component.section.CherrishSelectionSection
import com.cherrish.android.core.designsystem.component.type.CherrishSectionChipType
import com.cherrish.android.core.designsystem.theme.CherrishTheme
import kotlinx.collections.immutable.persistentListOf

@Composable
fun CategoryContent(
    selectedIndex: Int?,
    onItemClick: (Int) -> Unit
) {
    CherrishSelectionSection(
        title = "요즘 가장 신경 쓰이는\n피부 고민은 무엇인가요?",
        description = "선택한 고민을 기준으로 시술 정보를 정리해줘요.",
        descriptionTextStyle = CherrishTheme.typography.body1M14,
        items = persistentListOf("피부결 ∙ 각질", "색소 ∙ 잡티", "홍조", "탄력 ∙ 주름", "모공", "트러블"),
        selectedIndex = selectedIndex,
        onItemClick = onItemClick,
        chipType = CherrishSectionChipType.SELECTION_CHIP
    )
}

@Preview(showBackground = true)
@Composable
private fun CategoryContentPreview() {
    var selectedIndex by remember { mutableIntStateOf(-1) }

    CategoryContent(
        selectedIndex = selectedIndex,
        onItemClick = { selectedIndex = it }
    )
}