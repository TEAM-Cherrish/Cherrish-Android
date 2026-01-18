package com.cherrish.android.core.designsystem.component.type

import androidx.annotation.DrawableRes
import com.cherrish.android.R

enum class CherrishGaugeType(
    val step: Int,
    val percent: Int,
    @DrawableRes val image: Int
) {
    LEVEL1(step = 1, percent = 25, image = R.drawable.img_onboarding_lv1),
    LEVEL2(step = 2, percent = 50, image = R.drawable.img_onboarding_lv2),
    LEVEL3(step = 3, percent = 75, image = R.drawable.img_onboarding_lv3),
    LEVEL4(step = 4, percent = 100, image = R.drawable.img_onboarding_lv4)
}
