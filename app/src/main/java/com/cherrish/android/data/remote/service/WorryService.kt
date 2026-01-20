package com.cherrish.android.data.remote.service

import com.cherrish.android.core.network.BaseResponse
import com.cherrish.android.data.remote.dto.response.WorryResponseDto
import retrofit2.http.GET

interface WorryService {
    @GET("api/worries")
    suspend fun getWorries(): BaseResponse<List<WorryResponseDto>>
}
