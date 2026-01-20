package com.cherrish.android.presentation.home

import androidx.compose.runtime.Immutable
import com.cherrish.android.core.designsystem.component.type.CherrishGaugeType
import com.cherrish.android.data.model.RecentProcedureModel
import com.cherrish.android.data.model.UpcomingProcedureModel
import java.time.LocalDate
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList

@Immutable
data class HomeUiState(
    val currentStep: Int,
    val gauges: ImmutableList<CherrishGaugeType>,
    val challengeName: String?,
    val challengeRate: Int,
    val todayDate: String,
    val plans: ImmutableList<RecentProcedureModel>,
    val upcomingPlans: ImmutableList<UpcomingProcedureModel>,
    val selectedIndex: Int
) {
    companion object {
        val fake = HomeUiState(
            currentStep = 1,
            gauges = CherrishGaugeType.entries.toImmutableList(),
            challengeName = "웰니스 • 마음챙김",
            challengeRate = 80,
            todayDate = "2026년 1월 1일 (목)",
            plans = List(10) {
                RecentProcedureModel(
                    procedureName = "슈링크",
                    daysSince = 2,
                    downtimePhase = ""
                )
            }.toImmutableList(),
            upcomingPlans = persistentListOf(
                UpcomingProcedureModel(
                    upcomingPlanDate = LocalDate.of(2026, 1, 20),
                    procedureName = "써마지",
                    procedureCount = 2,
                    dDay = 3
                ),
                UpcomingProcedureModel(
                    upcomingPlanDate = LocalDate.of(2026, 1, 20),
                    procedureName = "써마지",
                    procedureCount = 2,
                    dDay = 3
                )
            ),
            selectedIndex = 0
        )
    }
}

sealed interface HomeSideEffect {
    data object NavigateToChallenge : HomeSideEffect
    data object NavigateToCalendar : HomeSideEffect
}
