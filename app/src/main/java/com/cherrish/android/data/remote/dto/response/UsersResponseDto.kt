package com.cherrish.android.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UsersResponseDto(
    @SerialName("name")
    val name: String,
    @SerialName("daysSinceSignup")
    val daysSinceSignUp: Int
)
