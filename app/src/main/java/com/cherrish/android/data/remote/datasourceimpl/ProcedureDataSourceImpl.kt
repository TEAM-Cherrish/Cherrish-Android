package com.cherrish.android.data.remote.datasourceimpl

import com.cherrish.android.core.network.BaseResponse
import com.cherrish.android.data.remote.datasource.ProcedureDataSource
import com.cherrish.android.data.remote.dto.response.ProceduresResponseDto
import com.cherrish.android.data.remote.service.ProcedureService
import javax.inject.Inject

class ProcedureDataSourceImpl @Inject constructor(
    private val procedureService: ProcedureService
) : ProcedureDataSource {
    override suspend fun getProcedures(
        keyword: String?,
        worryId: Long?
    ): BaseResponse<ProceduresResponseDto> =
        procedureService.getProcedures(keyword = keyword, worryId = worryId)
}
