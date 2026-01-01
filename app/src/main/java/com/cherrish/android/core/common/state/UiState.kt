package com.cherrish.android.core.common.state

sealed interface UiState<out T> {
    data object Empty : UiState<Nothing>

    data object Loading : UiState<Nothing>

    data class Success<T>(
        val data: T
    ) : UiState<T>

    data object Failure : UiState<Nothing>
}
