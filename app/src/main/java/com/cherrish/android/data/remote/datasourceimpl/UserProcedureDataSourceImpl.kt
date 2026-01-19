package com.cherrish.android.data.remote.datasourceimpl

import com.cherrish.android.core.network.BaseResponse
import com.cherrish.android.data.remote.datasource.UserProcedureDataSource
import com.cherrish.android.data.remote.dto.request.UserProceduresRequestDto
import com.cherrish.android.data.remote.dto.response.UserProceduresResponseDto
import com.cherrish.android.data.remote.service.UserProcedureService
import javax.inject.Inject

class UserProcedureDataSourceImpl @Inject constructor(
    private val userProcedureService: UserProcedureService
) : UserProcedureDataSource {

    override suspend fun addUserProcedures(
        body: UserProceduresRequestDto
    ): BaseResponse<UserProceduresResponseDto> =
        userProcedureService.addUserProcedures(body = body)
}
