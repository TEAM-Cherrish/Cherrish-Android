package com.cherrish.android.data.remote.datasourceimpl

import com.cherrish.android.core.network.BaseResponse
import com.cherrish.android.data.remote.datasource.DummyDataSource
import com.cherrish.android.data.remote.dto.response.DummyResponseDto
import com.cherrish.android.data.remote.service.DummyService
import javax.inject.Inject

class DummyDataSourceImpl @Inject constructor(
    private val dummyService: DummyService
) : DummyDataSource {
    override suspend fun getDummyData(): BaseResponse<DummyResponseDto> =
        dummyService.getDummy()
}
