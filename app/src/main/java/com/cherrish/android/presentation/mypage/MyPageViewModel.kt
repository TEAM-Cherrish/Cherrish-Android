package com.cherrish.android.presentation.mypage

import androidx.lifecycle.ViewModel
import com.cherrish.android.core.common.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel
@Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow<UiState<MyPageUiState>>(
        UiState.Success(MyPageUiState.FakeUsers)
        )
    val uiState: StateFlow<UiState<MyPageUiState>> = _uiState.asStateFlow()
}
