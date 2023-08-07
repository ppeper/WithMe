package com.bonobono.domain.repository

import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.mission.Mission

interface MissionRepository {
    suspend fun getOXQuiz(memberId: Int) : NetworkResult<Mission>
    suspend fun getFourQuiz(memberId: Int) : NetworkResult<Mission>
    suspend fun getMiniGame(memberId: Int) : NetworkResult<Mission>
}