package com.cherrish.android.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cherrish.android.core.common.extension.onLogFailure
import com.cherrish.android.core.common.state.UiState
import com.cherrish.android.data.model.toTodayDateString
import com.cherrish.android.data.repository.HomeRepository
import com.cherrish.android.presentation.home.type.CherrishGaugeType
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import javax.inject.Inject
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<HomeUiState>>((UiState.Loading))
    val uiState: StateFlow<UiState<HomeUiState>> = _uiState.asStateFlow()

    private val _sideEffect = MutableSharedFlow<HomeSideEffect>()
    val sideEffect: SharedFlow<HomeSideEffect> = _sideEffect.asSharedFlow()

    init {
        loadHomeData()
    }

    private fun loadHomeData() {
        viewModelScope.launch {
            _uiState.update { UiState.Loading }

            homeRepository.getMainDashboard().onSuccess { response ->
                _uiState.update {
                    UiState.Success(
                        HomeUiState(
                            currentStep = response.cherryLevel,
                            gauges = CherrishGaugeType.entries.toImmutableList(),
                            challengeRate = response.challengeRate,
                            challengeName = response.challengeName,
                            todayDate = response.toTodayDateString(),
                            plans = response.recentProcedures.toImmutableList(),
                            upcomingPlans = response.upcomingProcedures.toImmutableList(),
                            selectedIndex = response.cherryLevel
                        )
                    )
                }
            }.onLogFailure {}
        }
    }

    fun onUpcomingPlanClick(date: LocalDate) {
        viewModelScope.launch {
            _sideEffect.emit(
                HomeSideEffect.NavigateToCalendar
            )
        }
    }

    fun onAddChallengeClick() {
        viewModelScope.launch {
            _sideEffect.emit(
                HomeSideEffect.NavigateToChallenge
            )
        }
    }

    fun onAddPlanClick() {
    }
}
