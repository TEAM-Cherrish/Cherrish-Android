package com.cherrish.android.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChallengeMissionResponseDto(
    @SerialName("challengeId")
    val challengeId: Long,
    @SerialName("challengeName")
    val challengeName: String


)
