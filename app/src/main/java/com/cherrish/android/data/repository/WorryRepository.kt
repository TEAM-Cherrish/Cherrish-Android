package com.cherrish.android.data.repository

import com.cherrish.android.data.model.WorryModel

interface WorryRepository {
    suspend fun getWorries(): Result<List<WorryModel>>
}
