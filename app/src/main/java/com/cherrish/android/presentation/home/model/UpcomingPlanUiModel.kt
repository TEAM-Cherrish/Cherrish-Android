package com.cherrish.android.presentation.home.model

import androidx.compose.runtime.Immutable
import java.time.LocalDate

@Immutable
data class UpcomingPlanUiModel(
    val upcomingPlanDate: LocalDate,
    val procedureName: String,
    val procedureCount: Int,
    val dDay: Int
)
