package com.cherrish.android.presentation.home.type

import androidx.annotation.DrawableRes
import com.cherrish.android.R

enum class CherrishGaugeType(
    val step: Int,
    @DrawableRes val image: Int
) {
    LEVEL0(step = 0, image = R.drawable.img_home_lv1),
    LEVEL1(step = 1, image = R.drawable.img_home_lv1),
    LEVEL2(step = 2, image = R.drawable.img_home_lv2),
    LEVEL3(step = 3, image = R.drawable.img_home_lv3),
    LEVEL4(step = 4, image = R.drawable.img_home_lv4)
}
