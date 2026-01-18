package com.cherrish.android.presentation.challenge.mission

import androidx.compose.runtime.Immutable
import com.cherrish.android.presentation.challenge.mission.model.ChallengeMissionModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Immutable
data class ChallengeMissionUiState(
    val missions: ImmutableList<ChallengeMissionModel> = persistentListOf()
)
