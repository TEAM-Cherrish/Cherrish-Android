package com.cherrish.android.data.repositoryimpl

import com.cherrish.android.core.util.suspendRunCatching
import com.cherrish.android.data.datasource.DummyDataSource
import com.cherrish.android.data.model.DummyModel
import com.cherrish.android.data.model.toModel
import com.cherrish.android.data.repository.DummyRepository
import javax.inject.Inject

class DummyRepositoryImpl @Inject constructor(
    private val dummyDataSource: DummyDataSource
) : DummyRepository {
    override suspend fun getDummy(): Result<DummyModel> =
        suspendRunCatching {
            dummyDataSource.getDummyData().data!!.toModel()
        }
}
