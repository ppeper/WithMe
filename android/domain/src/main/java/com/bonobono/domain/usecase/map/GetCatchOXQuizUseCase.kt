package com.bonobono.domain.usecase.map

import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.mission.Mission
import com.bonobono.domain.repository.MissionRepository
import javax.inject.Inject

class GetCatchOXQuizUseCase @Inject constructor(
    private val missionRepository: MissionRepository
) {
    suspend operator fun invoke(memberId: Int): Mission {
        return when(val result = missionRepository.getCatchOXQuiz(memberId)) {
            is NetworkResult.Success -> result.data
            else -> Mission()
        }
    }
}