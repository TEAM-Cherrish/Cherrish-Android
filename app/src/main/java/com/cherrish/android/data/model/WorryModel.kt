package com.cherrish.android.data.model

import com.cherrish.android.data.remote.dto.response.WorryResponseDto

data class WorryModel(
    val id: Long,
    val content: String
)

fun WorryResponseDto.toModel() = WorryModel(
    id = id,
    content = content
)
