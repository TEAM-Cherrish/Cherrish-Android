package com.cherrish.android.presentation.calendar.procedure.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.cherrish.android.R
import com.cherrish.android.core.designsystem.theme.CherrishTheme
import com.cherrish.android.presentation.calendar.component.ProcedureTextField
import com.cherrish.android.presentation.calendar.procedure.component.CautionDescription
import com.cherrish.android.presentation.calendar.procedure.component.ProcedureCard
import com.cherrish.android.presentation.calendar.procedure.model.ProcedureCardDisplayMode
import com.cherrish.android.presentation.calendar.procedure.model.ProcedureCardItemUiModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

/* TODO: 삭제 예정 */
private val mockProcedureCardItems = persistentListOf(
    ProcedureCardItemUiModel(
        id = 1L,
        name = "레이저 토닝",
        category = "색소 개선 | 토닝",
        minDowntimeDays = 3,
        maxDowntimeDays = 5,
        displayMode = ProcedureCardDisplayMode.Basic
    ),
    ProcedureCardItemUiModel(
        id = 2L,
        name = "레이저 토닝",
        category = "색소 개선 | 토닝",
        minDowntimeDays = 2,
        maxDowntimeDays = 3,
        displayMode = ProcedureCardDisplayMode.Basic
    ),
    ProcedureCardItemUiModel(
        id = 3L,
        name = "레이저 토닝",
        category = "색소 개선 | 토닝",
        minDowntimeDays = 5,
        maxDowntimeDays = 8,
        displayMode = ProcedureCardDisplayMode.Basic
    ),
    ProcedureCardItemUiModel(
        id = 4L,
        name = "레이저 토닝",
        category = "색소 개선 | 토닝",
        minDowntimeDays = 3,
        maxDowntimeDays = 5,
        displayMode = ProcedureCardDisplayMode.Basic
    ),
    ProcedureCardItemUiModel(
        id = 5L,
        name = "레이저 토닝",
        category = "색소 개선 | 토닝",
        minDowntimeDays = 3,
        maxDowntimeDays = 5,
        displayMode = ProcedureCardDisplayMode.Basic
    ),
    ProcedureCardItemUiModel(
        id = 6L,
        name = "레이저 토닝",
        category = "색소 개선 | 토닝",
        minDowntimeDays = 3,
        maxDowntimeDays = 5,
        displayMode = ProcedureCardDisplayMode.Basic
    ),
    ProcedureCardItemUiModel(
        id = 7L,
        name = "레이저 토닝",
        category = "색소 개선 | 토닝",
        minDowntimeDays = 3,
        maxDowntimeDays = 5,
        displayMode = ProcedureCardDisplayMode.Basic
    )
)

@Composable
fun FilteringWithSearchContent(
    cardItems: ImmutableList<ProcedureCardItemUiModel>,
    selectedCardIds: ImmutableList<Long>,
    onCardClick: (Long) -> Unit,
    onSearchAction: (String) -> Unit,
    query: String,
    onQueryChange: (String) -> Unit,
    bottomPadding: Dp,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        ProcedureTextField(
            value = query,
            onValueChange = onQueryChange,
            onSearchAction = { onSearchAction(query) },
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(bottom = bottomPadding)
        ) {
            item {
                CautionDescription()
            }

            item {
                Spacer(modifier = Modifier.height(10.dp))
            }

            if (cardItems.isEmpty()) {
                item {
                    EmptySearchResult(
                        modifier = Modifier.fillParentMaxSize()
                    )
                }
            } else {
                items(
                    items = cardItems,
                    key = { it.id }
                ) { item ->
                    ProcedureCard(
                        procedureName = item.name,
                        category = item.category,
                        minDowntimeDays = item.minDowntimeDays,
                        maxDowntimeDays = item.maxDowntimeDays,
                        onCardClick = { onCardClick(item.id) },
                        isSelected = item.id in selectedCardIds,
                        displayMode = item.displayMode
                    )
                }
            }
        }
    }
}

@Composable
private fun EmptySearchResult(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Spacer(modifier = Modifier.height(130.dp))

        Image(
            painter = painterResource(id = R.drawable.img_procedure_no_search),
            contentDescription = null,
            modifier = Modifier.size(98.dp, 106.dp)

        )

        Text(
            text = "찾으시는 시술이 없습니다.\n" +
                "다른 시술명을 입력해보세요.",
            textAlign = TextAlign.Center,
            style = CherrishTheme.typography.body1R14,
            color = CherrishTheme.colors.gray600
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun FilteringWithSearchContentPreview() {
    CherrishTheme {
        var selectedCardIds by remember { mutableStateOf(persistentListOf<Long>()) }
        var query by remember { mutableStateOf("") }

        FilteringWithSearchContent(
            cardItems = mockProcedureCardItems,
            selectedCardIds = selectedCardIds,
            onCardClick = { clickedId ->
                selectedCardIds =
                    if (clickedId in selectedCardIds) {
                        selectedCardIds.remove(clickedId)
                    } else {
                        selectedCardIds.add(clickedId)
                    }
            },
            onSearchAction = {},
            query = query,
            bottomPadding = 20.dp,
            onQueryChange = { query = it }
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun FilteringWithSearchContentEmptyPreview() {
    CherrishTheme {
        FilteringWithSearchContent(
            cardItems = persistentListOf(),
            selectedCardIds = persistentListOf(),
            onCardClick = {},
            onSearchAction = {},
            query = "레이저 토닝",
            bottomPadding = 20.dp,
            onQueryChange = {}
        )
    }
}
