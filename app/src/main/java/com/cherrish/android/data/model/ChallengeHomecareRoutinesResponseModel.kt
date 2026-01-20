package com.cherrish.android.data.model

import androidx.compose.runtime.Immutable
import com.cherrish.android.data.remote.dto.response.ChallengeHomecareRoutinesResponseDto

@Immutable
data class ChallengeHomecareRoutinesResponseModel(
   val id: Int,
    val name: String,
    val description : String
)

fun ChallengeHomecareRoutinesResponseDto.toModel() = ChallengeHomecareRoutinesResponseModel(
    id = this.id,
    name = this.name,
    description = this.description
)
