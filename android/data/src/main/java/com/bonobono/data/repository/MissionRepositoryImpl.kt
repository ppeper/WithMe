package com.bonobono.data.repository

import com.bonobono.data.local.PreferenceDataSource
import com.bonobono.data.mapper.toDomain
import com.bonobono.data.remote.MissionService
import com.bonobono.data.remote.handleApi
import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.mission.IsSuccess
import com.bonobono.domain.model.mission.Mission
import com.bonobono.domain.model.mission.TotalScore
import com.bonobono.domain.repository.MissionRepository
import javax.inject.Inject

class MissionRepositoryImpl @Inject constructor(
    private val preferenceDatasource: PreferenceDataSource,
    private val missionService: MissionService
) : MissionRepository {

    override suspend fun getOXQuiz(memberId: Int): NetworkResult<Mission> {
        return handleApi { missionService.getOXQuiz(memberId = memberId).toDomain() }
    }

    override suspend fun postIsSuccessOXQuiz(isSuccess: IsSuccess) : NetworkResult<Boolean> {
        return handleApi { missionService.postOXQuizIsSuccess(isSuccess) }
    }

    override suspend fun getFourQuiz(memberId: Int): NetworkResult<Mission> {
        return handleApi { missionService.getFourQuiz(memberId = memberId).toDomain() }
    }

    override suspend fun postIsSuccessFourQuiz(memberId: Int, problemId: Int, answer: String) : NetworkResult<Boolean> {
        return handleApi { missionService.postFourQuizIsSuccess(memberId, problemId, answer) }
    }

    override suspend fun getMiniGame(memberId: Int): NetworkResult<Mission> {
        return handleApi { missionService.getMiniGame(memberId = memberId).toDomain() }
    }


    override suspend fun postAttendance(memberId: Int) {
        handleApi { missionService.postAttendance(memberId = memberId) }
    }

    override suspend fun getTotalScore(memberId: Int): NetworkResult<TotalScore> {
        return handleApi { missionService.getTotalScore(memberId = memberId).toDomain() }
    }

    override fun getCompletedTime(key: String): Long {
        return preferenceDatasource.getLong(key)
    }

    override suspend fun putCompletedTime(key: String, time: Long) {
        preferenceDatasource.putLong(key, time)
    }

    override suspend fun removeCompletedTime(key: String) {

    }


}