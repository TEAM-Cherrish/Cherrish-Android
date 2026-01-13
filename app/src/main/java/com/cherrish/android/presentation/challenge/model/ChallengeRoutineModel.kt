package com.cherrish.android.presentation.challenge.model

data class ChallengeRoutineModel(
    val id: Int,
    val name : String,
    val description : String,
    val isSelected: Boolean = false
)
