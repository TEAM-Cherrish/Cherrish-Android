package com.cherrish.android.data.repositoryimpl

import com.cherrish.android.core.util.suspendRunCatching
import com.cherrish.android.data.model.OnboardingProfileRequestModel
import com.cherrish.android.data.model.OnboardingProfileResponseModel
import com.cherrish.android.data.model.toDto
import com.cherrish.android.data.model.toModel
import com.cherrish.android.data.remote.datasource.OnboardingProfileDataSource
import com.cherrish.android.data.repository.OnboardingProfileRepository
import javax.inject.Inject

class OnboardingProfileRepositoryImpl @Inject constructor(
    private val onboardingProfileDataSource: OnboardingProfileDataSource
) : OnboardingProfileRepository{
    override suspend fun postOnboardingProfile(request: OnboardingProfileRequestModel): Result<OnboardingProfileResponseModel> =
        suspendRunCatching {
            onboardingProfileDataSource.postOnboardingProfile(request = request.toDto()).data!!.toModel()
        }
}
