package com.cherrish.android.data.model

import com.cherrish.android.data.remote.dto.response.OnboardingProfileResponseDto

data class OnboardingProfileResponseModel(
    val id: Long,
    val name: String,
    val date: String
)

fun OnboardingProfileResponseDto.toModel() = OnboardingProfileResponseModel(
    id = this.id,
    name = this.name,
    date = this.date
)
