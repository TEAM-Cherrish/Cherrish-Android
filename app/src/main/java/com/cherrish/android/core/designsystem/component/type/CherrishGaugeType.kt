package com.cherrish.android.core.designsystem.component.type

enum class CherrishGaugeType(
    val step: Int,
    val percent: Int
) {
    LEVEL1(step = 1, percent = 25),
    LEVEL2(step = 2, percent = 50),
    LEVEL3(step = 3, percent = 75),
    LEVEL4(step = 4, percent = 100)
}
