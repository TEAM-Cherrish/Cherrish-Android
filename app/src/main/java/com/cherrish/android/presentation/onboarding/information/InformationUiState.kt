package com.cherrish.android.presentation.onboarding.information

import androidx.compose.runtime.Immutable

@Immutable
data class InformationUiState(
    val username: String = "",
    val age: String = ""
) {
    val buttonEnabled: Boolean
        get() {
            val ageInt = age.toIntOrNull() ?: return false
            return username.isNotBlank() && age.toIntOrNull()?.let { it > 0 } == true &&
                ageInt in 1..100 && username.length <= 7
        }
}

sealed interface InformationSideEffect {
    data object NavigateToHome : InformationSideEffect
}
