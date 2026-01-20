package com.cherrish.android.data.repositoryimpl

import com.cherrish.android.core.util.suspendRunCatching
import com.cherrish.android.data.model.UsersResponseModel
import com.cherrish.android.data.model.toModel
import com.cherrish.android.data.remote.datasource.MyPageDataSource
import com.cherrish.android.data.repository.MyPageRepository
import javax.inject.Inject

class MyPageRepositoryImpl @Inject constructor(
    private val myPageDataSource: MyPageDataSource
) : MyPageRepository {
    override suspend fun getUsers(): Result<UsersResponseModel> =
        suspendRunCatching {
            myPageDataSource.getUsers().data!!.toModel()
        }
}
