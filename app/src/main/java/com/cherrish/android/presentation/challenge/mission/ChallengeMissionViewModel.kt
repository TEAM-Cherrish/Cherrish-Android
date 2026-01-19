package com.cherrish.android.presentation.challenge.mission

import androidx.lifecycle.ViewModel
import com.cherrish.android.core.common.extension.updateSuccess
import com.cherrish.android.core.common.state.UiState
import com.cherrish.android.presentation.challenge.mission.model.ChallengeMissionModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ChallengeMissionViewModel @Inject constructor() : ViewModel(){
    private val _uiState =
        MutableStateFlow<UiState<ChallengeMissionUiState>>(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        loadMissions()
    }

    private fun loadMissions(){
        _uiState.value = UiState.Success(
            ChallengeMissionUiState(
                missions = persistentListOf(   ChallengeMissionModel(1, "아침 세안 후 토너 바르기"),
                    ChallengeMissionModel(2, "수분 에센스 2-3방울 흡수"),
                    ChallengeMissionModel(3, "보습 크림으로 마무리"),
                    ChallengeMissionModel(4, "저녁 클렌징 꼼꼼히 하기"),
                    ChallengeMissionModel(5, "수분 마스크팩 (주 2-3회)")
                )

            )
        )
    }
    fun onTodoMissionClick(id:Long){
        _uiState.updateSuccess {
            state -> state.copy(
                missions = state.missions.map {
                    mission -> if(mission.id==id){
                        mission.copy(isSelected = !mission.isSelected)
                }else {
                    mission
                }
                }.toPersistentList()
            )
        }
    }


    fun onAddTodoClick() {
        val state = _uiState.value

        if (state !is UiState.Success) return

        if (!state.data.hasSelected) return

        state.data.selectedMissions

    }
    fun onBackClick(){}
    fun onCloseClick(){}

}
