package com.cherrish.android.presentation.challenge.model

data class ChallengeRoutineMissionModel(
   override val id: Int,
   override val title : String,
 val subTitle: String,
   override val category : String,
   override val isSelected: Boolean = false
) : ChallengeRoutineItem
