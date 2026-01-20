package com.cherrish.android.data.remote.datasource

import com.cherrish.android.core.network.BaseResponse
import com.cherrish.android.data.remote.dto.response.ChallengeMissionResponseDto
import com.cherrish.android.data.remote.dto.response.ChallengeRoutineCompleteResponseDto

interface ChallengeMissionDataSource {
    suspend fun getChallengeMissions(): BaseResponse<ChallengeMissionResponseDto>
    suspend fun patchChallengeRoutinesComplete(routineId: Long): BaseResponse<ChallengeRoutineCompleteResponseDto>
}
