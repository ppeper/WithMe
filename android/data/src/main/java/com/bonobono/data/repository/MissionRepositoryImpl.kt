package com.bonobono.data.repository

import com.bonobono.data.mapper.toDomain
import com.bonobono.data.remote.MissionService
import com.bonobono.data.remote.handleApi
import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.mission.Mission
import com.bonobono.domain.model.mission.TotalScore
import com.bonobono.domain.repository.MissionRepository
import javax.inject.Inject

class MissionRepositoryImpl @Inject constructor(
    private val missionService: MissionService
) : MissionRepository {

    override suspend fun getOXQuiz(memberId: Int): NetworkResult<Mission> {
        return handleApi { missionService.getOXQuiz(memberId = memberId).toDomain() }
    }

    override suspend fun getFourQuiz(memberId: Int): NetworkResult<Mission> {
        return handleApi { missionService.getFourQuiz(memberId = memberId).toDomain() }
    }

    override suspend fun getMiniGame(memberId: Int): NetworkResult<Mission> {
        return handleApi { missionService.getMiniGame(memberId = memberId).toDomain() }
    }

    override suspend fun getTotalScore(memberId: Int): NetworkResult<TotalScore> {
        return handleApi { missionService.getTotalScore(memberId = memberId).toDomain() }
    }

}