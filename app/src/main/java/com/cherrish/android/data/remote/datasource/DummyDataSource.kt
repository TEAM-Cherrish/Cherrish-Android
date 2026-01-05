package com.cherrish.android.data.remote.datasource

import com.cherrish.android.core.network.BaseResponse
import com.cherrish.android.data.remote.dto.response.DummyResponseDto

interface DummyDataSource {
    suspend fun getDummyData(): BaseResponse<DummyResponseDto>
}
