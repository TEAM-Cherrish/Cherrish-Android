package com.cherrish.android.core.local.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.cherrish.android.core.local.TokenManager
import com.cherrish.android.core.local.TokenManagerImpl
import com.cherrish.android.core.local.constant.DataStoreConstant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    private val Context.userInfoDataStore by preferencesDataStore(
        name = DataStoreConstant.CHERRISH_USER_INFO_PREFS
    )

    @Provides
    @Singleton
    fun provideUserInfoDataStore(
        @ApplicationContext context: Context
    ): DataStore<Preferences> = context.userInfoDataStore

    @Provides
    @Singleton
    fun provideTokenManager(
        dataStore: DataStore<Preferences>
    ): TokenManager = TokenManagerImpl(dataStore)
}
