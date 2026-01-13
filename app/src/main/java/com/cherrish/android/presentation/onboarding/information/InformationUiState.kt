package com.cherrish.android.presentation.onboarding.information

import androidx.compose.runtime.Immutable

@Immutable
data class InformationUiState(
    val username: String = "",
    val age: String = "",
){
    val buttonEnabled: Boolean
        get() = username.isNotBlank() && age.isNotBlank()
}
