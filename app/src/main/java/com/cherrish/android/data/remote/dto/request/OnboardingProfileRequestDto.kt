package com.cherrish.android.data.remote.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OnboardingProfileRequestDto(
    @SerialName("name")
    val name: String,
    @SerialName("age")
    val age: Int
)
