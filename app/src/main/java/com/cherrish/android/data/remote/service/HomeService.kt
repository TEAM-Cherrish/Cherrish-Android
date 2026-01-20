package com.cherrish.android.data.remote.service

import com.cherrish.android.core.network.BaseResponse
import com.cherrish.android.data.remote.dto.response.HomeResponseDto
import retrofit2.http.GET

interface HomeService {
    @GET("api/main-dashboard")
    suspend fun getMainDashboard(): BaseResponse<HomeResponseDto>
}
