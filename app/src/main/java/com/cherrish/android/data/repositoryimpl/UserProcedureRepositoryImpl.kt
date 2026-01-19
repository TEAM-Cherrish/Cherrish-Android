package com.cherrish.android.data.repositoryimpl

import com.cherrish.android.core.util.suspendRunCatching
import com.cherrish.android.data.model.UserProceduresRequestModel
import com.cherrish.android.data.model.UserProceduresResponseModel
import com.cherrish.android.data.model.toDto
import com.cherrish.android.data.model.toModel
import com.cherrish.android.data.remote.datasource.UserProcedureDataSource
import com.cherrish.android.data.repository.UserProcedureRepository
import javax.inject.Inject

class UserProcedureRepositoryImpl @Inject constructor(
    private val userProcedureDataSource: UserProcedureDataSource
) : UserProcedureRepository {

    override suspend fun addUserProcedures(
        body: UserProceduresRequestModel
    ): Result<UserProceduresResponseModel> =
        suspendRunCatching {
            userProcedureDataSource
                .addUserProcedures(body = body.toDto())
                .data!!
                .toModel()
        }
}
