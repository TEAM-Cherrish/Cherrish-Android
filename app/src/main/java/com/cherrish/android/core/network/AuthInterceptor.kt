package com.cherrish.android.core.network

import com.cherrish.android.core.local.TokenManager
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber

@Singleton
class AuthInterceptor @Inject constructor(
    private val tokenManager: TokenManager
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val id = runBlocking { tokenManager.getId() }
        Timber.d("User ID: $id")

        val originalRequest = chain.request()

        val authRequest = if (id != null) {
            originalRequest.newBuilder()
                .header("X-User-Id", id.toString())
                .build()
        } else {
            originalRequest
        }

        return chain.proceed(authRequest)
    }
}
