package com.cherrish.android.data.remote.datasourceimpl

import com.cherrish.android.core.network.BaseResponse
import com.cherrish.android.data.remote.datasource.HomeDataSource
import com.cherrish.android.data.remote.dto.response.HomeResponseDto
import com.cherrish.android.data.remote.service.HomeService
import javax.inject.Inject

class HomeDataSourceImpl @Inject constructor(
    private val homeService: HomeService
) : HomeDataSource {
    override suspend fun getMainDashboard(): BaseResponse<HomeResponseDto> =
        homeService.getMainDashboard()
}
