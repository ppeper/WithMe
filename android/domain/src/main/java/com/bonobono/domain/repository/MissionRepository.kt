package com.bonobono.domain.repository

import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.mission.IsSuccess
import com.bonobono.domain.model.mission.Mission
import com.bonobono.domain.model.mission.TotalScore

interface MissionRepository {
    suspend fun getOXQuiz(memberId: Int) : NetworkResult<Mission>
    suspend fun postIsSuccessOXQuiz(isSuccess: IsSuccess) : NetworkResult<Boolean>
    suspend fun getFourQuiz(memberId: Int) : NetworkResult<Mission>
    suspend fun postIsSuccessFourQuiz(memberId: Int, problemId: Int, answer: String) : NetworkResult<Boolean>
    suspend fun getMiniGame(memberId: Int) : NetworkResult<Mission>
    suspend fun postAttendance(memberId: Int)
    suspend fun getTotalScore(memberId: Int) : NetworkResult<TotalScore>
}