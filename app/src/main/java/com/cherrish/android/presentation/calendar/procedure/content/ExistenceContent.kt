package com.cherrish.android.presentation.calendar.procedure.content

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cherrish.android.core.designsystem.theme.CherrishTheme
import com.cherrish.android.presentation.calendar.procedure.component.SelectionSection
import kotlinx.collections.immutable.persistentListOf

@Composable
fun ExistenceContent(
    selectedIndex: Int?,
    onItemClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    SelectionSection(
        title = "시술 일정을 추가해볼게요.\n" +
            "이미 생각해둔 시술이 있나요?",
        description = "시술을 선택하셨는지 확인할게요.",
        descriptionTextStyle = CherrishTheme.typography.body1R14,
        items = persistentListOf("선택한 시술이 있어요", "아직 선택 전이에요"),
        selectedIndex = selectedIndex,
        onItemClick = onItemClick
    )
}

@Preview(showBackground = true)
@Composable
private fun ExistenceContentPreview() {
    CherrishTheme {
        var selectedIndex by remember { mutableIntStateOf(-1) }

        ExistenceContent(
            selectedIndex = selectedIndex,
            onItemClick = { selectedIndex = it }
        )
    }
}
