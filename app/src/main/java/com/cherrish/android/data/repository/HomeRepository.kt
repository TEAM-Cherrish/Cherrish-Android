package com.cherrish.android.data.repository

import com.cherrish.android.data.model.HomeResponseModel

interface HomeRepository {
    suspend fun getMainDashboard(): Result<HomeResponseModel>
}
