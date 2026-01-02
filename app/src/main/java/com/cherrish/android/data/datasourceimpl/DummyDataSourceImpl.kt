package com.cherrish.android.data.datasourceimpl

import com.cherrish.android.core.network.BaseResponse
import com.cherrish.android.data.datasource.DummyDataSource
import com.cherrish.android.data.dto.response.DummyResponseDto
import com.cherrish.android.data.service.DummyService
import javax.inject.Inject

class DummyDataSourceImpl @Inject constructor(
    private val dummyService: DummyService
) : DummyDataSource {
    override suspend fun getDummyData(): BaseResponse<DummyResponseDto> =
        dummyService.getDummy()
}
