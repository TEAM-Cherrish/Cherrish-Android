package com.cherrish.android.data.repository

import com.cherrish.android.data.model.ProceduresResponseModel

interface ProcedureRepository {
    suspend fun getProcedures(
        keyword: String? = null,
        worryId: Long? = null
    ): Result<ProceduresResponseModel>
}
