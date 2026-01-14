package com.cherrish.android.presentation.mypage

import androidx.lifecycle.ViewModel
import com.cherrish.android.core.common.state.UiState
import com.cherrish.android.presentation.mypage.model.MyPageInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel
@Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow<UiState<MyPageUiState>>(
        UiState.Success(MyPageUiState.Users)
        )
    val uiState: StateFlow<UiState<MyPageUiState>> = _uiState.asStateFlow()
//    init {
//        loadMyPage()
//    }
//    private fun loadMyPage(){
//        val model = MyPageInfo(
//            nicknameText = "홍길동",
//            skinCareDay = 3
//        )
//        _uiState.update {
//            it.copy()
//        }
//    }
}
