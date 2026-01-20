package com.cherrish.android.data.remote.datasourceimpl

import com.cherrish.android.core.network.BaseResponse
import com.cherrish.android.data.remote.datasource.WorryDataSource
import com.cherrish.android.data.remote.dto.response.WorryResponseDto
import com.cherrish.android.data.remote.service.WorryService
import javax.inject.Inject

class WorryDataSourceImpl @Inject constructor(
    private val worryService: WorryService
) : WorryDataSource {
    override suspend fun getWorries(): BaseResponse<List<WorryResponseDto>> =
        worryService.getWorries()
}
