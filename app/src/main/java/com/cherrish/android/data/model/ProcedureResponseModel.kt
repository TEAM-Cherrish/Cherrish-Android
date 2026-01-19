package com.cherrish.android.data.model

import com.cherrish.android.data.remote.dto.response.ProcedureDto
import com.cherrish.android.data.remote.dto.response.ProceduresResponseDto

data class ProceduresResponseModel(
    val procedures: List<ProcedureModel>
)

data class ProcedureModel(
    val id: Long,
    val name: String,
    val minDowntimeDays: Int,
    val maxDowntimeDays: Int
)

fun ProceduresResponseDto.toModel() = ProceduresResponseModel(
    procedures = procedures.map { it.toModel() }
)

fun ProcedureDto.toModel() = ProcedureModel(
    id = id,
    name = name,
    minDowntimeDays = minDowntimeDays,
    maxDowntimeDays = maxDowntimeDays
)
