package com.cherrish.android.data.repository

import com.cherrish.android.data.model.OnboardingProfileRequestModel
import com.cherrish.android.data.model.OnboardingProfileResponseModel
import com.cherrish.android.data.remote.dto.response.ChallengeRoutineDto
import com.cherrish.android.data.remote.dto.response.ChallengesHomecareRoutinesResponseDto

interface ChallengeRepository{
    suspend fun getChallengeRoutinData(

    ): Result<ChallengesHomecareRoutinesResponseDto>
}
