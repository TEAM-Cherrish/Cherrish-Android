package com.cherrish.android.data.repositoryimpl

import com.cherrish.android.core.util.suspendRunCatching
import com.cherrish.android.data.model.WorryModel
import com.cherrish.android.data.model.toModel
import com.cherrish.android.data.remote.datasource.WorryDataSource
import com.cherrish.android.data.repository.WorryRepository
import javax.inject.Inject

class WorryRepositoryImpl @Inject constructor(
    private val worryDataSource: WorryDataSource
) : WorryRepository {
    override suspend fun getWorries(): Result<List<WorryModel>> =
        suspendRunCatching {
            worryDataSource.getWorries().data!!.map { it.toModel() }
        }
}
