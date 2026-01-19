package com.cherrish.android.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WorryResponseDto(
    @SerialName("id")
    val id: Long,
    @SerialName("content")
    val content: String
)
