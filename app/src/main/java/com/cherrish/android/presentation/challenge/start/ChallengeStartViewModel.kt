package com.cherrish.android.presentation.challenge.start

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cherrish.android.presentation.challenge.ChallengeSideEffect
import com.cherrish.android.presentation.home.HomeSideEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import okhttp3.Challenge
import javax.inject.Inject

@HiltViewModel
class ChallengeStartViewModel @Inject constructor() :
    ViewModel() {
        private val _sideEffect = MutableSharedFlow<ChallengeSideEffect>()
    val sideEffect: SharedFlow<ChallengeSideEffect> =_sideEffect.asSharedFlow()

    fun onNextClick() {
        viewModelScope.launch {
            _sideEffect.emit(
                ChallengeSideEffect.navigateToMission
            )
        }
    }
}
