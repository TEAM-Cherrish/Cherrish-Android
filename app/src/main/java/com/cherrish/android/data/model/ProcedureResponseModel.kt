package com.cherrish.android.data.model

import com.cherrish.android.data.remote.dto.response.ProcedureDto
import com.cherrish.android.data.remote.dto.response.ProceduresResponseDto

data class ProceduresResponseModel(
    val procedures: List<ProcedureModel>
)

data class ProcedureModel(
    val id: Long,
    val name: String,
    val category: String?,
    val minDowntimeDays: Int,
    val maxDowntimeDays: Int
)

fun ProceduresResponseDto.toModel() = ProceduresResponseModel(
    procedures = this.procedures.map { it.toModel() }
)

fun ProcedureDto.toModel() = ProcedureModel(
    id = this.id,
    name = this.name,
    category = this.category?.takeIf { it.isNotBlank() }
        ?: this.worries.map { it.content }.filter { it.isNotBlank() }.joinToString(" | "),
    minDowntimeDays = this.minDowntimeDays,
    maxDowntimeDays = this.maxDowntimeDays
)
