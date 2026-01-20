package com.cherrish.android.presentation.challenge.start

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cherrish.android.presentation.challenge.ChallengeSideEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

@HiltViewModel
class ChallengeStartViewModel @Inject constructor() :
    ViewModel() {
    private val _sideEffect = MutableSharedFlow<ChallengeSideEffect>()
    val sideEffect: SharedFlow<ChallengeSideEffect> = _sideEffect.asSharedFlow()

    fun onNextClick() {
        viewModelScope.launch {
            _sideEffect.emit(
                ChallengeSideEffect.navigateToMission
            )
        }
    }
}
