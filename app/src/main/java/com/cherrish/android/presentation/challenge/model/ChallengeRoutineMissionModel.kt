package com.cherrish.android.presentation.challenge.model

data class ChallengeRoutineMissionModel(
    val id: Int,
    val title : String,
    val subTitle: String,
    val category : String,
    val isSelected: Boolean = false
)
