package com.cherrish.android.core.network

import com.cherrish.android.core.local.TokenManager
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import timber.log.Timber

@Singleton
class TokenAuthenticator @Inject constructor(
    private val tokenManager: TokenManager
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        Timber.d("인증 실패 (401). 아이디 갱신 필요")

        runBlocking {
            tokenManager.clearId()
        }

        return null
    }
}
