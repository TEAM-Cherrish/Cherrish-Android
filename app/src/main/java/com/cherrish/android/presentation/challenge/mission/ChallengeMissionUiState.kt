package com.cherrish.android.presentation.challenge.mission

import androidx.compose.runtime.Immutable
import com.cherrish.android.presentation.challenge.mission.model.ChallengeMissionModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList

@Immutable
data class ChallengeMissionUiState(
    val missions: ImmutableList<ChallengeMissionModel>,
    val routineId: Int? = null
) {
    val selectedMissions: ImmutableList<ChallengeMissionModel>
        get() = missions.filter { it.isSelected }.toPersistentList()

    val isSelected: Boolean
        get() = selectedMissions.isNotEmpty()

    companion object {
        val Fake = ChallengeMissionUiState(
            missions = persistentListOf(
                ChallengeMissionModel(
                    id = 1L,
                    missionContent = "아침 세안 후 토너 바르기",
                    isSelected = false
                ),
                ChallengeMissionModel(
                    id = 2L,
                    missionContent = "수분 에센스 2-3방울 흡수",
                    isSelected = false
                ),
                ChallengeMissionModel(
                    id = 3L,
                    missionContent = "보습 크림으로 마무리",
                    isSelected = false
                ),
                ChallengeMissionModel(
                    id = 4L,
                    missionContent = "저녁 클렌징 꼼꼼히 하기",
                    isSelected = false
                ),
                ChallengeMissionModel(
                    id = 5L,
                    missionContent = "수분 마스크팩 (주 2-3회)",
                    isSelected = false
                )
            )
        )
    }
}

sealed interface ChallengeMissionSideEffect {
    data object NavigateToChallengeMissionProgress : ChallengeMissionSideEffect
    data object NavigateToChallengeRoutine : ChallengeMissionSideEffect
}

