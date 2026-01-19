package com.cherrish.android.data.remote.datasource

import com.cherrish.android.core.network.BaseResponse
import com.cherrish.android.data.remote.dto.response.HomeResponseDto

interface HomeDataSource {
    suspend fun getMainDashboard(): BaseResponse<HomeResponseDto>
}
