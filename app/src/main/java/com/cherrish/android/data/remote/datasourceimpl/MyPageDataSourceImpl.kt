package com.cherrish.android.data.remote.datasourceimpl

import com.cherrish.android.core.network.BaseResponse
import com.cherrish.android.data.remote.datasource.MyPageDataSource
import com.cherrish.android.data.remote.dto.response.UsersResponseDto
import com.cherrish.android.data.remote.service.MyPageService
import javax.inject.Inject

class MyPageDataSourceImpl @Inject constructor(
    private val myPageService: MyPageService
) : MyPageDataSource {
    override suspend fun getUsersProfile(): BaseResponse<UsersResponseDto> =
        myPageService.getUsersProfile()
}
