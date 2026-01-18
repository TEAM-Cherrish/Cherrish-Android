package com.cherrish.android.data.repository

import com.cherrish.android.data.model.OnboardingProfileRequestModel
import com.cherrish.android.data.model.OnboardingProfileResponseModel

interface OnboardingProfileRepository {
    suspend fun postOnboardingProfile(
        request: OnboardingProfileRequestModel
    ): Result<OnboardingProfileResponseModel>
}
