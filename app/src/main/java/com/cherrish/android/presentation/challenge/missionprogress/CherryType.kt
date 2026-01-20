package com.cherrish.android.presentation.challenge.missionprogress

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
import com.cherrish.android.R

@Immutable
enum class CherryType(
    val step: Int,
    val stageName: String,
    @DrawableRes val imageRes: Int
) {
    MONGRONG(
        step = 1,
        stageName = "몽롱체리",
        imageRes = R.drawable.img_challenge_lv1
    ),
    PPODUK(
        step = 2,
        stageName = "뽀득체리",
        imageRes = R.drawable.img_challenge_lv2
    ),
    BBANGBBANG(
        step = 3,
        stageName = "팡팡체리",
        imageRes = R.drawable.img_challenge_lv3
    ),
    KKUKKU(
        step = 4,
        stageName = "꾸꾸체리",
        imageRes = R.drawable.img_challenge_lv4
    )
}
