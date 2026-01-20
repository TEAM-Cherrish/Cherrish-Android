package com.cherrish.android.data.remote.service

import com.cherrish.android.core.network.BaseResponse
import com.cherrish.android.data.remote.dto.response.UsersResponseDto
import retrofit2.http.GET

interface MyPageService {
    @GET("api/users")
    suspend fun getUsers(): BaseResponse<UsersResponseDto>
}
