package com.cherrish.android.data.remote.datasource

import com.cherrish.android.core.network.BaseResponse
import com.cherrish.android.data.remote.dto.request.OnboardingProfileRequestDto
import com.cherrish.android.data.remote.dto.response.OnboardingProfileResponseDto

interface OnboardingProfileDataSource {
    suspend fun postOnboardingProfile(
        request: OnboardingProfileRequestDto
    ): BaseResponse<OnboardingProfileResponseDto>
}
