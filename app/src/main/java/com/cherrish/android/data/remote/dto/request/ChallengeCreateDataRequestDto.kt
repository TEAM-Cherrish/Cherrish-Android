package com.cherrish.android.data.remote.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChallengeCreateDataRequestDto(
    @SerialName("homecareRoutineId")
    val homecareRoutineId: Int,

    @SerialName("routineNames")
    val routineNames: List<String>
)
