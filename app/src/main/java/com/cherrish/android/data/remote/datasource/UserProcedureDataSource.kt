package com.cherrish.android.data.remote.datasource

import com.cherrish.android.core.network.BaseResponse
import com.cherrish.android.data.remote.dto.request.UserProceduresRequestDto
import com.cherrish.android.data.remote.dto.response.UserProceduresResponseDto

interface UserProcedureDataSource {
    suspend fun addUserProcedures(
        request: UserProceduresRequestDto
    ): BaseResponse<UserProceduresResponseDto>
}
