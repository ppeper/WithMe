package com.bonobono.domain.usecase.mission

import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.mission.TotalScore
import com.bonobono.domain.repository.MissionRepository
import javax.inject.Inject

class GetTotalScoreUseCase @Inject constructor(
    private val missionRepository: MissionRepository
) {
    suspend operator fun invoke(memberId: Int): TotalScore {
        return when(val result = missionRepository.getTotalScore(1)) {
            is NetworkResult.Success -> result.data
            else -> TotalScore(-1, -1)
        }
    }
}