package com.cherrish.android.presentation.calendar.procedure.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cherrish.android.core.designsystem.theme.CherrishTheme
import com.cherrish.android.presentation.calendar.procedure.component.ProcedureCard
import com.cherrish.android.presentation.calendar.procedure.component.ProcedureTitleSection
import com.cherrish.android.presentation.calendar.procedure.model.ProcedureCardDisplayMode
import com.cherrish.android.presentation.calendar.procedure.model.ProcedureCardItemUiModel

/* TODO: 삭제 예정 */
private val mockProcedureCardItems = listOf(
    ProcedureCardItemUiModel(
        id = "1",
        name = "레이저 토닝",
        category = "색소 개선 | 토닝",
        minDowntimeDays = 3,
        maxDowntimeDays = 5,
        displayMode = ProcedureCardDisplayMode.Basic
    ),
    ProcedureCardItemUiModel(
        id = "2",
        name = "레이저 토닝",
        category = "색소 개선 | 토닝",
        minDowntimeDays = 2,
        maxDowntimeDays = 3,
        displayMode = ProcedureCardDisplayMode.Basic
    ),
    ProcedureCardItemUiModel(
        id = "3",
        name = "레이저 토닝",
        category = "색소 개선 | 토닝",
        minDowntimeDays = 5,
        maxDowntimeDays = 8,
        displayMode = ProcedureCardDisplayMode.Basic
    ),
    ProcedureCardItemUiModel(
        id = "4",
        name = "레이저 토닝",
        category = "색소 개선 | 토닝",
        minDowntimeDays = 3,
        maxDowntimeDays = 5,
        displayMode = ProcedureCardDisplayMode.Basic
    ),
    ProcedureCardItemUiModel(
        id = "5",
        name = "레이저 토닝",
        category = "색소 개선 | 토닝",
        minDowntimeDays = 3,
        maxDowntimeDays = 5,
        displayMode = ProcedureCardDisplayMode.Basic
    ),
    ProcedureCardItemUiModel(
        id = "6",
        name = "레이저 토닝",
        category = "색소 개선 | 토닝",
        minDowntimeDays = 3,
        maxDowntimeDays = 5,
        displayMode = ProcedureCardDisplayMode.Basic
    ),
    ProcedureCardItemUiModel(
        id = "7",
        name = "레이저 토닝",
        category = "색소 개선 | 토닝",
        minDowntimeDays = 3,
        maxDowntimeDays = 5,
        displayMode = ProcedureCardDisplayMode.Basic
    )
)

@Composable
fun FilteringContent(
    cardItems: List<ProcedureCardItemUiModel>,
    selectedCardId: String?,
    onCardClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        ProcedureTitleSection(
            procedureName = "색소 ∙ 잡티"
        )
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 19.dp)
        ) {
            itemsIndexed(
                items = cardItems,
                key = { _, item -> item.id }
            ) { _, item ->
                ProcedureCard(
                    name = item.name,
                    category = item.category,
                    minDowntimeDays = item.minDowntimeDays,
                    maxDowntimeDays = item.maxDowntimeDays,
                    onCardClick = { onCardClick(item.id) },
                    isSelected = selectedCardId == item.id,
                    displayMode = item.displayMode
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun FilteringContentPreview() {
    CherrishTheme {
        var selectedCardId by remember { mutableStateOf<String?>(null) }

        FilteringContent(
            cardItems = mockProcedureCardItems,
            selectedCardId = selectedCardId,
            onCardClick = { clickedId ->
                selectedCardId = if (selectedCardId == clickedId) null else clickedId
            }
        )
    }
}
