package com.cherrish.android.data.remote.service

import com.cherrish.android.core.network.BaseResponse
import com.cherrish.android.data.remote.dto.request.UserProceduresRequestDto
import com.cherrish.android.data.remote.dto.response.UserProceduresResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface UserProcedureService {
    @POST("api/user-procedures")
    suspend fun addUserProcedures(
        @Body body: UserProceduresRequestDto
    ): BaseResponse<UserProceduresResponseDto>
}
