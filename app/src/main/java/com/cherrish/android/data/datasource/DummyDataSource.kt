package com.cherrish.android.data.datasource

import com.cherrish.android.core.network.BaseResponse
import com.cherrish.android.data.dto.response.DummyResponseDto

interface DummyDataSource {
    suspend fun getDummyData(): BaseResponse<DummyResponseDto>
}
