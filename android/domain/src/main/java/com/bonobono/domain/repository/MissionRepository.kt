package com.bonobono.domain.repository

import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.mission.IsSuccess
import com.bonobono.domain.model.mission.MiniGame
import com.bonobono.domain.model.mission.Mission
import com.bonobono.domain.model.mission.TotalScore

interface MissionRepository {
    suspend fun getOXQuiz(memberId: Int): NetworkResult<Mission>
    suspend fun postIsSuccessOXQuiz(isSuccess: IsSuccess): NetworkResult<Boolean>
    suspend fun getFourQuiz(memberId: Int): NetworkResult<Mission>
    suspend fun postIsSuccessFourQuiz(isSuccess: IsSuccess): NetworkResult<Boolean>
    suspend fun getMiniGame(memberId: Int): NetworkResult<MiniGame>
    suspend fun postIsSuccessMiniGame(isSuccess: IsSuccess): NetworkResult<Boolean>
    suspend fun postAttendance(memberId: Int)
    suspend fun getTotalScore(memberId: Int): NetworkResult<TotalScore>
    fun getCompletedTime(key: String): Long
    suspend fun putCompletedTime(key: String, time: Long)
    suspend fun getCatchOXQuiz(memberId: Int): NetworkResult<Mission>
    suspend fun postCatchIsSuccessOXQuiz(isSuccess: IsSuccess): NetworkResult<Boolean>
    suspend fun removeCompletedTime()
    fun putBoolean(key: String, value: Boolean)
    fun getBoolean(key: String): Boolean
}