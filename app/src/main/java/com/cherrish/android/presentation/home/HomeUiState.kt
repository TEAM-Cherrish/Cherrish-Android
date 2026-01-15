package com.cherrish.android.presentation.home

import androidx.compose.runtime.Immutable
import com.cherrish.android.core.designsystem.component.type.CherrishGaugeType
import com.cherrish.android.presentation.home.model.PlanUiModel
import com.cherrish.android.presentation.home.model.UpcomingPlanUiModel
import com.cherrish.android.presentation.home.type.DowntimePhase
import java.time.LocalDate
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList

@Immutable
data class HomeUiState(
    val currentStep: Int,
    val gauges: ImmutableList<CherrishGaugeType>,
    val todayDate: String,
    val plans: ImmutableList<PlanUiModel>,
    val upcomingPlans: ImmutableList<UpcomingPlanUiModel>,
    val selectedIndex: Int
) {
    companion object {
        val fake = HomeUiState(
            currentStep = 1,
            gauges = CherrishGaugeType.entries.toImmutableList(),
            todayDate = "2026년 1월 1일 (목)",
            plans = List(10) {
                PlanUiModel(
                    procedureName = "슈링크",
                    daysSince = 2,
                    downtimePhase = DowntimePhase.SENSITIVE
                )
            }.toImmutableList(),
            upcomingPlans = persistentListOf(
                UpcomingPlanUiModel(
                    upcomingPlanDate = LocalDate.now().plusDays(3),
                    procedureName = "써마지",
                    procedureCount = 2,
                    dDay = 3
                ),
                UpcomingPlanUiModel(
                    upcomingPlanDate = LocalDate.now().plusDays(3),
                    procedureName = "써마지",
                    procedureCount = 2,
                    dDay = 3
                )
            ),
            selectedIndex = 0
        )
    }
}
