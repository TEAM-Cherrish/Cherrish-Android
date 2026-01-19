package com.cherrish.android.data.repository

import com.cherrish.android.data.model.UserProceduresRequestModel
import com.cherrish.android.data.model.UserProceduresResponseModel

interface UserProcedureRepository {
    suspend fun addUserProcedures(
        body: UserProceduresRequestModel
    ): Result<UserProceduresResponseModel>
}
