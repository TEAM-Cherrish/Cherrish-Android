package com.cherrish.android.data.repositoryimpl

import com.cherrish.android.core.util.suspendRunCatching
import com.cherrish.android.data.model.ProceduresResponseModel
import com.cherrish.android.data.model.toModel
import com.cherrish.android.data.remote.datasource.ProcedureDataSource
import com.cherrish.android.data.repository.ProcedureRepository
import javax.inject.Inject

class ProcedureRepositoryImpl @Inject constructor(
    private val procedureDataSource: ProcedureDataSource
) : ProcedureRepository {
    override suspend fun getProcedures(
        keyword: String?,
        worryId: Long?
    ): Result<ProceduresResponseModel> =
        suspendRunCatching {
            procedureDataSource
                .getProcedures(keyword = keyword, worryId = worryId).data!!.toModel()
        }
}
