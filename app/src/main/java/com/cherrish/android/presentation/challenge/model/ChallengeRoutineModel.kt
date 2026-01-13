package com.cherrish.android.presentation.challenge.model

data class ChallengeRoutineModel(
    override val id: Int,
    override val title : String,
    override val category : String,
    override val isSelected: Boolean = false
) : ChallengeRoutineItem
