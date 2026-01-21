package com.cherrish.android.data.model

import com.cherrish.android.data.remote.dto.response.UsersResponseDto

data class UsersResponseModel(
    val name: String,
    val daysSinceSignUp: Int
)

fun UsersResponseDto.toModel() = UsersResponseModel(
    name = this.name,
    daysSinceSignUp = this.daysSinceSignUp
)
