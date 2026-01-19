package com.cherrish.android.data.remote.datasourceimpl

import com.cherrish.android.core.network.BaseResponse
import com.cherrish.android.data.remote.datasource.ChallengeDataSource
import com.cherrish.android.data.remote.dto.response.ChallengesHomecareRoutinesResponseDto
import com.cherrish.android.data.remote.service.ChallengeService
import javax.inject.Inject

class ChallengeDataSourceImpl @Inject constructor(
    private val challengeService : ChallengeService
) : ChallengeDataSource {
    override suspend fun getHomecareRoutineData(

    ): BaseResponse<ChallengesHomecareRoutinesResponseDto> {
        return challengeService.getHomecareRoutineData()

    }
}
