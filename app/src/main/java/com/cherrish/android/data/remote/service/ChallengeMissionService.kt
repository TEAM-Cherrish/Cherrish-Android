package com.cherrish.android.data.remote.service

import com.cherrish.android.core.network.BaseResponse
import com.cherrish.android.data.remote.dto.response.ChallengeMissionResponseDto
import retrofit2.http.GET

interface ChallengeMissionService {
    @GET("api/demo/challenges")
    fun getChallengeMissions(): BaseResponse<ChallengeMissionResponseDto>
}
