package com.cherrish.android.data.model

import com.cherrish.android.data.remote.dto.response.ChallengesHomecareRoutinesResponseDto

data class ChallengesHomecareRoutinesResponseModel(
    val id: Int,
    val name: String,
    val description: String?
)

fun ChallengesHomecareRoutinesResponseDto.toModel() = ChallengesHomecareRoutinesResponseModel(
    id = this.id,
    name = this.name,
    description = this.description
)
