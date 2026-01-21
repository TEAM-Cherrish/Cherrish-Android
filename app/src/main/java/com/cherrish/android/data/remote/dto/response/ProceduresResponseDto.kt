package com.cherrish.android.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProceduresResponseDto(
    @SerialName("procedures")
    val procedures: List<ProcedureDto>
)

@Serializable
data class ProcedureDto(
    @SerialName("id")
    val id: Long,
    @SerialName("name")
    val name: String,
    @SerialName("category")
    val category: String? = null,
    @SerialName("worries")
    val worries: List<WorryResponseDto> = emptyList(),
    @SerialName("minDowntimeDays")
    val minDowntimeDays: Int,
    @SerialName("maxDowntimeDays")
    val maxDowntimeDays: Int
)
