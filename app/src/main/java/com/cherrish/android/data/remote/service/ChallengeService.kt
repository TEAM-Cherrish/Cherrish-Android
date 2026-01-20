package com.cherrish.android.data.remote.service

import com.cherrish.android.core.network.BaseResponse
import com.cherrish.android.data.remote.dto.response.ChallengeHomecareRoutinesResponseDto
import retrofit2.http.GET

interface ChallengeService {
    @GET("api/challenges/homecare-routines")
    suspend fun getHomecareRoutineData(): BaseResponse<List<ChallengeHomecareRoutinesResponseDto>>
}
