package com.cherrish.android.presentation.mypage.model

import androidx.compose.runtime.Immutable

@Immutable
data class MyPageInfo(
    val nickname: String,
    val skinCareDay: Int
)
