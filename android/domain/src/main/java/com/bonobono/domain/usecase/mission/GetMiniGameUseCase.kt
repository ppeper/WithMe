package com.bonobono.domain.usecase.mission

import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.mission.Mission
import com.bonobono.domain.repository.MissionRepository
import javax.inject.Inject

class GetMiniGameUseCase @Inject constructor(
    private val missionRepository: MissionRepository
) {
    suspend operator fun invoke(memberId: Int): NetworkResult<Mission> {
        return missionRepository.getMiniGame(memberId)
    }
}