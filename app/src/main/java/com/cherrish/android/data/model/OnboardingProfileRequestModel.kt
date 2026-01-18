package com.cherrish.android.data.model

import com.cherrish.android.data.remote.dto.request.OnboardingProfileRequestDto

data class OnboardingProfileRequestModel(
    val name: String,
    val age: Int
)

fun OnboardingProfileRequestModel.toDto() = OnboardingProfileRequestDto(
    name = this.name,
    age = this.age
)
