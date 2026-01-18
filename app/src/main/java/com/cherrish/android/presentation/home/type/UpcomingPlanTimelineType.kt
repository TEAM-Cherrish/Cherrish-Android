package com.cherrish.android.presentation.home.type

enum class UpcomingPlanTimelineType {
    FIRST, SECOND, THIRD
}

fun Int.toUpcomingPlanTimelineType(): UpcomingPlanTimelineType = when (this) {
    0 -> UpcomingPlanTimelineType.FIRST
    1 -> UpcomingPlanTimelineType.SECOND
    else -> UpcomingPlanTimelineType.THIRD
}
