package com.cherrish.android.data.repository

import com.cherrish.android.data.model.UsersResponseModel

interface MyPageRepository {
    suspend fun getUsersProfile(): Result<UsersResponseModel>
}
