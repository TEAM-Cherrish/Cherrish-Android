package com.cherrish.android.presentation.home

import androidx.lifecycle.ViewModel
import com.cherrish.android.core.common.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<HomeUiState>>(
        UiState.Success(HomeUiState.fake)
    )
    val uiState: StateFlow<UiState<HomeUiState>> = _uiState.asStateFlow()

    fun onUpcomingPlanClick(date: LocalDate) {
    }

    fun onAddChallengeClick(){

    }

    fun onAddPlanClick() {
    }
}
