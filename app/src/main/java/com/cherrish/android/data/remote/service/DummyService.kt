package com.cherrish.android.data.remote.service

import com.cherrish.android.core.network.BaseResponse
import com.cherrish.android.data.remote.dto.response.DummyResponseDto

interface DummyService {
    suspend fun getDummy(): BaseResponse<DummyResponseDto>
}
