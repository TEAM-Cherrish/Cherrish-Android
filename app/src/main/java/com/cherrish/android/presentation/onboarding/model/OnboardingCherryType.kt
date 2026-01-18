package com.cherrish.android.presentation.onboarding.model

import androidx.annotation.DrawableRes
import com.cherrish.android.R

enum class OnboardingCherryType(
    @DrawableRes val image: Int,
    val index: Int
) {
    LV0(image = R.drawable.img_onboarding_lv0, index = 0),
    LV1(image = R.drawable.img_onboarding_lv1, index = 1),
    LV2(image = R.drawable.img_onboarding_lv2, index = 2),
    LV3(image = R.drawable.img_onboarding_lv3, index = 3),
    LV4(image = R.drawable.img_onboarding_lv4, index = 4)
}
