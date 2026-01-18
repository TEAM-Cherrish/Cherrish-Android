package com.cherrish.android.presentation.home.model

import androidx.compose.runtime.Immutable
import com.cherrish.android.presentation.home.type.DowntimePhase

@Immutable
data class PlanUiModel(
    val procedureName: String,
    val daysSince: Int,
    val downtimePhase: DowntimePhase
)
