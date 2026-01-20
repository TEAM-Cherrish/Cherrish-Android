package com.cherrish.android.data.remote.service

import com.cherrish.android.core.network.BaseResponse
import com.cherrish.android.data.remote.dto.response.ChallengeMissionResponseDto
import com.cherrish.android.data.remote.dto.response.ChallengeRoutineCompleteResponseDto
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface ChallengeMissionService {
    @GET("api/demo/challenges")
    fun getChallengeMissions(): BaseResponse<ChallengeMissionResponseDto>

    @PATCH("api/demo/challenges/routines/{routineId}/toggle")
    fun patchChallengeRoutinesComplete(
        @Path("routineId") routineId: Long
    ): BaseResponse<ChallengeRoutineCompleteResponseDto>
}
