package com.cherrish.android.data.service

import com.cherrish.android.core.network.BaseResponse
import com.cherrish.android.data.dto.response.DummyResponseDto

interface DummyService {
    suspend fun getDummy(): BaseResponse<DummyResponseDto>
}
