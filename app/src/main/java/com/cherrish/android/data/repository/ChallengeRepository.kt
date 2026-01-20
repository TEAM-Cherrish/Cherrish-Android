package com.cherrish.android.data.repository

import com.cherrish.android.data.model.ChallengeHomecareRoutinesResponseModel

interface ChallengeRepository {
    suspend fun getChallengeRoutineData(): Result<List<ChallengeHomecareRoutinesResponseModel>>
}
