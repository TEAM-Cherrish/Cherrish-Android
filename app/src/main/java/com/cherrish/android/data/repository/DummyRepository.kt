package com.cherrish.android.data.repository

import com.cherrish.android.data.model.DummyModel

interface DummyRepository {
    suspend fun getDummy(): Result<DummyModel>
}
