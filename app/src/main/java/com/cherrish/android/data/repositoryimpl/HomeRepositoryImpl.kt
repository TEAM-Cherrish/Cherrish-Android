package com.cherrish.android.data.repositoryimpl

import com.cherrish.android.core.util.suspendRunCatching
import com.cherrish.android.data.model.HomeResponseModel
import com.cherrish.android.data.model.toModel
import com.cherrish.android.data.remote.datasource.HomeDataSource
import com.cherrish.android.data.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeDataSource: HomeDataSource
) : HomeRepository {
    override suspend fun getMainDashboard(): Result<HomeResponseModel> =
        suspendRunCatching {
            homeDataSource.getMainDashboard().data!!.toModel()
        }
}
