package com.cherrish.android.presentation.mypage

import androidx.compose.runtime.Immutable
import com.cherrish.android.presentation.mypage.model.MyPageInfo
import kotlinx.collections.immutable.persistentListOf

@Immutable
data class MyPageUiState(
    val nicknameText: String = "",
    val skinCareDay: Int = 0
) {
    companion object {
        val Users =
            MyPageUiState(
                nicknameText = "홍길동",
                skinCareDay = 3
            )

    }
}
