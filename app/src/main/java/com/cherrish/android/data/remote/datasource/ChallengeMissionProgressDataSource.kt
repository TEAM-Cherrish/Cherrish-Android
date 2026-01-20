package com.cherrish.android.data.remote.datasource

import com.cherrish.android.core.network.BaseResponse
import com.cherrish.android.data.remote.dto.response.ChallengeMissionProgressResponseDto
import com.cherrish.android.data.remote.dto.response.ChallengeRoutineCompleteResponseDto

interface ChallengeMissionProgressDataSource {
    suspend fun getChallengeMissions(): BaseResponse<ChallengeMissionProgressResponseDto>
    suspend fun patchChallengeRoutinesComplete(routineId: Long):
        BaseResponse<ChallengeRoutineCompleteResponseDto>
    suspend fun postChallengeAdvanceDay(): BaseResponse<ChallengeMissionProgressResponseDto>
}
