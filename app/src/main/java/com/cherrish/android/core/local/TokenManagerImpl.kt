package com.cherrish.android.core.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import javax.inject.Inject
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class TokenManagerImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : TokenManager {

    @Volatile
    private var cachedId: Long? = null

    override suspend fun saveId(id: Long) {
        cachedId = id
        dataStore.edit { preferences ->
            preferences[KEY_USER_ID] = id
        }
    }

    override suspend fun getId(): Long? {
        return cachedId ?: dataStore.data.map { preferences ->
            preferences[KEY_USER_ID]
        }.first().also {
            cachedId = it
        }
    }

    override suspend fun clearId() {
        cachedId = null
        dataStore.edit { preferences ->
            preferences.remove(KEY_USER_ID)
        }
    }

    companion object {
        private val KEY_USER_ID = longPreferencesKey("user_id")
    }
}
