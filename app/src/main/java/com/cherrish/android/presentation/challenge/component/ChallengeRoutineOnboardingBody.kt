package com.cherrish.android.presentation.challenge.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import com.cherrish.android.presentation.challenge.model.ChallengeRoutineCategory


@Composable
fun ChallengeRoutineOnboardingBody(
    selectedCategory: ChallengeRoutineCategory?,
    onCategoryClick: (ChallengeRoutineCategory) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = "지금 나에게 가장 필요한 \n관리 루틴을 선택해주세요.",
            color = CherrishTheme.colors.gray1000,
            style = CherrishTheme.typography.title1SB18
        )

        Spacer(modifier = Modifier.height(height = 40.dp))

        ChallengeRoutineSelectionChipGroup(
            categories = ChallengeRoutineCategory.entries,
            selectedCategory = selectedCategory,
            onCategoryClick = onCategoryClick
        )
    }
}

@Composable
private fun ChallengeRoutineCategoryChip(
    category: ChallengeRoutineCategory,
    isSelected: Boolean,
    onClick: (ChallengeRoutineCategory) -> Unit,
    modifier: Modifier = Modifier
) {
    CherrishSelectionChip(
        text = category.label,
        onClick = { onClick(category) },
        isSelected = isSelected
    )

}

@Composable
private fun ChallengeRoutineSelectionChipGroup(
    categories: List<ChallengeRoutineCategory>,
    selectedCategory: ChallengeRoutineCategory?,
    onCategoryClick: (ChallengeRoutineCategory) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(count = 2),
        horizontalArrangement = Arrangement.spacedBy(space = 12.dp),
        verticalArrangement = Arrangement.spacedBy(space = 12.dp),
        modifier = modifier
    ) {
        items(items = categories) { category ->

            ChallengeRoutineCategoryChip(
                category = category,
                isSelected = selectedCategory == category,
                onClick = onCategoryClick
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ChallengeRoutineSelectionChipGroupPreview() {
    CherrishTheme {
        var selectedCategory by remember { mutableStateOf<ChallengeRoutineCategory?>(null) }

        ChallengeRoutineSelectionChipGroup(
            categories = ChallengeRoutineCategory.entries,
            selectedCategory = selectedCategory,
            onCategoryClick = { selectedCategory = it },
            modifier = Modifier.padding(all = 12.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ChallengeRoutineOnboardingBodyPreview() {
    CherrishTheme {
        var selectedCategory by remember {
            mutableStateOf<ChallengeRoutineCategory?>(value = null)
        }

        ChallengeRoutineOnboardingBody(
            selectedCategory = selectedCategory,
            onCategoryClick = { selectedCategory = it },
            modifier = Modifier.padding(all = 26.dp)
        )
    }
}
