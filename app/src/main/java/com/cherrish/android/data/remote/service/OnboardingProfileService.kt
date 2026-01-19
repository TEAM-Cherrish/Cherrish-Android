package com.cherrish.android.data.remote.service

import com.cherrish.android.core.network.BaseResponse
import com.cherrish.android.data.remote.dto.request.OnboardingProfileRequestDto
import com.cherrish.android.data.remote.dto.response.OnboardingProfileResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface OnboardingProfileService {
    @POST("api/onboarding/profiles")
    suspend fun postOnboardingProfile(
        @Body request: OnboardingProfileRequestDto
    ): BaseResponse<OnboardingProfileResponseDto>
}
