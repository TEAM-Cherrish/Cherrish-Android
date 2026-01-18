package com.cherrish.android.data.remote.datasourceimpl

import com.cherrish.android.core.network.BaseResponse
import com.cherrish.android.data.remote.datasource.OnboardingProfileDataSource
import com.cherrish.android.data.remote.dto.request.OnboardingProfileRequestDto
import com.cherrish.android.data.remote.dto.response.OnboardingProfileResponseDto
import com.cherrish.android.data.remote.service.OnboardingProfileService
import javax.inject.Inject

class OnboardingProfileDataSourceImpl @Inject constructor(
    private val onboardingProfileService: OnboardingProfileService
) : OnboardingProfileDataSource {
    override suspend fun postOnboardingProfile(
        request: OnboardingProfileRequestDto
    ): BaseResponse<OnboardingProfileResponseDto> {
        return onboardingProfileService.postOnboardingProfile(request)
    }
}
