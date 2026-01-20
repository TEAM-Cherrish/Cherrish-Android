package com.cherrish.android.presentation.challenge.missionprogress

import androidx.lifecycle.ViewModel
import com.cherrish.android.core.common.extension.updateSuccess
import com.cherrish.android.core.common.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class ChallengeMissionProgressViewModel @Inject constructor() : ViewModel() {

    private val _uiState =
        MutableStateFlow<UiState<ChallengeMissionProgressUiState>>(UiState.Loading)
    val uiState: StateFlow<UiState<ChallengeMissionProgressUiState>> =
        _uiState.asStateFlow()

    init {
        loadMissions()
    }

    private fun loadMissions() {
//        _uiState.updateSuccess{
//
//        }
    }

    fun onTodoClick(id: Long) {
        _uiState.updateSuccess { state ->
            state.copy(
                routines = state.routines.map {
                    if (it.routineId == id) {
                        it.copy(isCompleted = !it.isCompleted)
                    } else {
                        it
                    }
                }.toPersistentList()
            )
        }
    }

    fun onCompletedTodayClick() {
        val state = _uiState.value
        if (state !is UiState.Success) return
        if (!state.data.hasCompletedAny) return
    }
}
