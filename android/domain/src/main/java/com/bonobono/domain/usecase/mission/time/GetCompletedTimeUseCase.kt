package com.bonobono.domain.usecase.mission.time

import com.bonobono.domain.repository.MissionRepository
import javax.inject.Inject

class GetCompletedTimeUseCase @Inject constructor(
    private val missionRepository: MissionRepository
) {
    operator fun invoke(key: String): Long {
        return missionRepository.getCompletedTime(key)
    }
}