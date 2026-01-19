package com.cherrish.android.core.designsystem.component.type

import androidx.annotation.DrawableRes
import com.cherrish.android.R

enum class CherrishGaugeType(
    val step: Int,
    @DrawableRes val image: Int
) {
    LEVEL1(step = 1, image = R.drawable.img_lv1),
    LEVEL2(step = 2, image = R.drawable.img_lv2),
    LEVEL3(step = 3, image = R.drawable.img_lv3),
    LEVEL4(step = 4, image = R.drawable.img_lv4)
}
