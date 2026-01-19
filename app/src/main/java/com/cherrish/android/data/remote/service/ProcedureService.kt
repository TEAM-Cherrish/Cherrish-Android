package com.cherrish.android.data.remote.service

import com.cherrish.android.core.network.BaseResponse
import com.cherrish.android.data.remote.dto.response.ProceduresResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ProcedureService {
    @GET("api/procedures")
    suspend fun getProcedures(
        @Query("keyword") keyword: String? = null,
        @Query("worryId") worryId: Long? = null
    ): BaseResponse<ProceduresResponseDto>
}
