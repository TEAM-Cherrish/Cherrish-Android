package com.cherrish.android.data.remote.datasource

import com.cherrish.android.core.network.BaseResponse
import com.cherrish.android.data.remote.dto.response.ChallengesHomecareRoutinesResponseDto
import com.cherrish.android.data.remote.dto.response.RoutinesDto


interface ChallengeDataSource {
    suspend fun getHomecareRoutineData(): BaseResponse<ChallengesHomecareRoutinesResponseDto>
}
