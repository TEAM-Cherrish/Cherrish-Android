package com.cherrish.android.presentation.challenge

import androidx.lifecycle.ViewModel
import com.cherrish.android.presentation.challenge.model.ChallengeRoutineCategory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ChallengeRouteViewModel : ViewModel() {
    private val _selectedCategory =
        MutableStateFlow<ChallengeRoutineCategory?>(null)
    val selectedCategory: StateFlow<ChallengeRoutineCategory?> = _selectedCategory

    fun onCategoryClick(category: ChallengeRoutineCategory) {
        _selectedCategory.value = category
    }

}
