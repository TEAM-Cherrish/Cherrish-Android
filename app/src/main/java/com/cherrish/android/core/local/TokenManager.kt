package com.cherrish.android.core.local

interface TokenManager {
    suspend fun saveId(id: Long)
    suspend fun getId(): Long?
    suspend fun clearId()
}
