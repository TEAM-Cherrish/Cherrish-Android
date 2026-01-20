package com.cherrish.android.presentation.challenge

sealed interface ChallengeSideEffect {
    data object NavigateToTodoRoutine : ChallengeSideEffect
}
