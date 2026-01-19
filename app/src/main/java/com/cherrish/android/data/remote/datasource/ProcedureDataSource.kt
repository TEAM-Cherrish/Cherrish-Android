package com.cherrish.android.data.remote.datasource

import com.cherrish.android.core.network.BaseResponse
import com.cherrish.android.data.remote.dto.response.ProceduresResponseDto

interface ProcedureDataSource {
    suspend fun getProcedures(
        keyword: String?,
        worryId: Long?
    ): BaseResponse<ProceduresResponseDto>
}
