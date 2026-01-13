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
fun ExistenceContent(
    existence: Boolean,
    selectedIndex: Int?,
    onItemClick: (Int) -> Unit
) {
    CherrishSelectionSection(
        title = "시술 일정을 추가해볼게요.",
        description = "시술을 선택하셨는지 확인할게요.",
        descriptionTextStyle = CherrishTheme.typography.body1R14,
        items = persistentListOf("선택한 시술이 있어요", "아직 선택 전이에요"),
        selectedIndex = selectedIndex,
        onItemClick = onItemClick,
        chipType = CherrishSectionChipType.SELECTION_CHIP
    )
}

@Preview(showBackground = true)
@Composable
private fun ExistenceContentPreview() {
    var selectedIndex by remember { mutableIntStateOf(-1) }

    ExistenceContent(
        existence = true,
        selectedIndex = selectedIndex,
        onItemClick = { selectedIndex = it }
    )
}