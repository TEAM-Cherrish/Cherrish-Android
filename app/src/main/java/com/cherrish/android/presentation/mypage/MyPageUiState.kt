package com.cherrish.android.presentation.mypage

import androidx.compose.runtime.Immutable

@Immutable
data class MyPageUiState(
    val nicknameText: String = "",
    val skinCareDay: Int = 0
) {
    companion object {
        val FakeUsers =
            MyPageUiState(
                nicknameText = "홍길동",
                skinCareDay = 3
            )
    }
}
