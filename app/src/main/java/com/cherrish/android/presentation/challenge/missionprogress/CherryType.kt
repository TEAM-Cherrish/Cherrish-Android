package com.cherrish.android.presentation.challenge.missionprogress

import androidx.annotation.DrawableRes
import com.cherrish.android.R

enum class CherryType(
    val step: Int,
    val level: Int,
    val stageName: String,
    @DrawableRes val imageRes: Int
) {
    MONGRONG(
        step = 0,
        level = 0,
        stageName = "몽롱체리",
        imageRes = R.drawable.img_challenge_lv0
    ),
    PPODUK(
        step = 1,
        level = 1,
        stageName = "뽀득체리",
        imageRes = R.drawable.img_challenge_lv1
    ),
    CHOKCHOK(
        step = 2,
        level = 2,
        stageName = "촉촉체리",
        imageRes = R.drawable.img_challenge_lv2
    ),
    BBANGBBANG(
        step = 3,
        level = 3,
        stageName = "팡팡체리",
        imageRes = R.drawable.img_challenge_lv3
    ),
    KKUKKU(
        step = 4,
        level = 4,
        stageName = "꾸꾸체리",
        imageRes = R.drawable.img_challenge_lv4
    )
}
