package com.cherrish.android.presentation.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cherrish.android.core.common.extension.onLogFailure
import com.cherrish.android.core.common.state.UiState
import com.cherrish.android.data.repository.MyPageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val myPageRepository: MyPageRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState<MyPageUiState>>(UiState.Loading)
    val uiState: StateFlow<UiState<MyPageUiState>> = _uiState.asStateFlow()

    init {
        loadMyPageProfile()
    }

    private fun loadMyPageProfile() {
        viewModelScope.launch {
            myPageRepository.getUsersProfile().onSuccess { response ->
                _uiState.update {
                    UiState.Success(
                        MyPageUiState(
                            nicknameText = response.name,
                            skinCareDay = response.daysSinceSignUp
                        )
                    )
                }
            }.onLogFailure { }
        }
    }
}
