package com.cherrish.android.data.remote.service

import com.cherrish.android.core.network.BaseResponse
import com.cherrish.android.data.remote.dto.response.ChallengeMissionProgressResponseDto
import com.cherrish.android.data.remote.dto.response.ChallengeRoutineCompleteResponseDto
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface ChallengeMissionProgressService {
    @GET("api/demo/challenges")
    suspend fun getChallengeMissions(): BaseResponse<ChallengeMissionProgressResponseDto>

    @PATCH("api/demo/challenges/routines/{routineId}/toggle")
    suspend fun patchChallengeRoutinesComplete(
        @Path("routineId") routineId: Long
    ): BaseResponse<ChallengeRoutineCompleteResponseDto>
}
