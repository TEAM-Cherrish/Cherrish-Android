package com.cherrish.android.presentation.challenge.mission

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.cherrish.android.core.common.extension.onLogFailure
import com.cherrish.android.core.common.extension.updateSuccess
import com.cherrish.android.core.common.state.UiState
import com.cherrish.android.data.repository.ChallengeRepository
import com.cherrish.android.presentation.challenge.mission.model.ChallengeMissionModel
import com.cherrish.android.presentation.challenge.navigation.ChallengeMission
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class ChallengeMissionViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val challengeRepository: ChallengeRepository
) : ViewModel() {

    private val routineIdArg = savedStateHandle.toRoute<ChallengeMission>().routineId
    private val routinesArgs = savedStateHandle.toRoute<ChallengeMission>().routines

    private val _uiState =
        MutableStateFlow<UiState<ChallengeMissionUiState>>(UiState.Loading)
    val uiState: StateFlow<UiState<ChallengeMissionUiState>> = _uiState.asStateFlow()

    private val _sideEffect = MutableSharedFlow<ChallengeMissionSideEffect>()
    val sideEffect: SharedFlow<ChallengeMissionSideEffect> = _sideEffect.asSharedFlow()

    init {
        loadMissions()
    }

    private fun loadMissions() {
        val missions = if (routinesArgs.isNotEmpty()) {
            routinesArgs.mapIndexed { index, mission ->
                ChallengeMissionModel(
                    id = index.toLong(),
                    missionContent = mission
                )
            }.toPersistentList()
        } else {
            ChallengeMissionUiState.Fake.missions
        }

        _uiState.update {
            UiState.Success(
                ChallengeMissionUiState(
                    missions = missions,
                    routineId = routineIdArg
                )
            )
        }
    }

    fun onTodoMissionClick(id: Long) {
        _uiState.updateSuccess { state ->
            state.copy(
                missions = state.missions.map { mission ->
                    if (mission.id == id) {
                        mission.copy(isSelected = !mission.isSelected)
                    } else {
                        mission
                    }
                }.toPersistentList()
            )
        }
    }

    fun onAddTodoClick() {
        val currentState = (_uiState.value as? UiState.Success)?.data ?: return
        val missions = currentState.selectedMissions
        if (missions.isEmpty()) return

        viewModelScope.launch {
            challengeRepository
                .postDemoChallenge(
                    homecareRoutineId = routineIdArg,
                    routineNames = missions.map { it.missionContent }
                )
                .onSuccess {
                    _sideEffect.emit(
                        ChallengeMissionSideEffect.NavigateToChallengeMissionProgress
                    )
                }.onLogFailure { }
        }
    }

    fun onBackClick() {
        viewModelScope.launch {
            _sideEffect.emit(ChallengeMissionSideEffect.NavigateToChallengeRoutine)
        }
    }

    fun onCloseClick() {
        viewModelScope.launch {
            _sideEffect.emit(ChallengeMissionSideEffect.NavigateToChallengeRoutine)
        }
    }
}
