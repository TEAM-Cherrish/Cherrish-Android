package com.cherrish.android.presentation.challenge.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cherrish.android.core.designsystem.component.chip.CherrishSelectionChip
import com.cherrish.android.core.designsystem.theme.CherrishTheme
import com.cherrish.android.presentation.challenge.routine.ChallengeRoutineUiState
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList

@Composable
fun ChallengeRoutineOnboardingBody(
    items: List<ChallengeRoutineUiState>,
    onItemClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "지금 나에게 가장 필요한 \n관리 루틴을 선택해주세요.",
            color = CherrishTheme.colors.gray1000,
            style = CherrishTheme.typography.title1SB18
        )

        Spacer(modifier = Modifier.height(height = 40.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(count = 2),
            horizontalArrangement = Arrangement.spacedBy(space = 12.dp),
            verticalArrangement = Arrangement.spacedBy(space = 12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            itemsIndexed(items) { index, item ->
                CherrishSelectionChip(
                    text = item.routine,
                    isSelected = item.isSelected,
                    onClick = { onItemClick(index) },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ChallengeRoutineOnboarding_RoutinePreview() {
    CherrishTheme {
        var routineItems by remember {
            mutableStateOf(
                value = persistentListOf(
                    ChallengeRoutineUiState(routine = "보습 루틴"),
                    ChallengeRoutineUiState(routine = "진정 루틴"),
                    ChallengeRoutineUiState(routine = "미백 루틴"),
                    ChallengeRoutineUiState(routine = "탄력 루틴"),
                    ChallengeRoutineUiState(routine = "모공 관리 루틴"),
                    ChallengeRoutineUiState(routine = "트러블 케어 루틴")
                )
            )
        }

        ChallengeRoutineOnboardingBody(
            items = routineItems,
            onItemClick = { clickedIndex ->
                routineItems = routineItems
                    .mapIndexed {
                                index, item ->
                        if(index == clickedIndex){
                            item.copy(isSelected = !item.isSelected)
                        }
                        else{
                            item.copy(isSelected = false)
                        }
                    }
                    .toPersistentList()
            },
            modifier = Modifier.padding(26.dp)
        )
    }
}
