package com.cherrish.android.presentation.main

import androidx.annotation.DrawableRes
import com.cherrish.android.R
import com.cherrish.android.core.common.navigation.MainTabRoute
import com.cherrish.android.presentation.calendar.navigation.Calendar
import com.cherrish.android.presentation.challenge.navigation.ChallengeStart
import com.cherrish.android.presentation.home.navigation.Home
import com.cherrish.android.presentation.mypage.navigation.MyPage

enum class MainTab(
    @DrawableRes val iconRes: Int,
    val route: MainTabRoute,
    val label: String
) {
    HOME(
        iconRes = R.drawable.ic_home,
        route = Home,
        label = "홈"
    ),
    CALENDAR(
        iconRes = R.drawable.ic_calendar,
        route = Calendar,
        label = "캘린더"
    ),
    CHALLENGE(
        iconRes = R.drawable.ic_challenge,
        route = ChallengeStart,
        label = "챌린지"
    ),
    MYPAGE(
        iconRes = R.drawable.ic_mypage,
        route = MyPage,
        label = "마이"
    );

    companion object {
        fun find(predicate: (MainTab) -> Boolean): MainTab? {
            return entries.find(predicate)
        }

        fun contains(predicate: (MainTab) -> Boolean): Boolean {
            return entries.any(predicate)
        }
    }
}
