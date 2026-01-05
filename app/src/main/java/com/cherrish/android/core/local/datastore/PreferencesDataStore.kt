package com.cherrish.android.core.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import javax.inject.Inject

class PreferencesDataStore @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

     /*예시 - 로그인 토큰 저장
     suspend fun saveToken(token: String) { ... }
     fun getToken(): Flow<String?> { ... }*/
}
