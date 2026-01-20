package com.cherrish.android.data.remote.datasource

import com.cherrish.android.core.network.BaseResponse
import com.cherrish.android.data.remote.dto.response.WorryResponseDto

interface WorryDataSource {
    suspend fun getWorries(): BaseResponse<List<WorryResponseDto>>
}
