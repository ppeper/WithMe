package com.bonobono.domain.usecase.mission.local

import com.bonobono.domain.repository.MissionRepository
import javax.inject.Inject

class PutCompletedTimeUseCase @Inject constructor(
    private val missionRepository: MissionRepository
) {
    suspend operator fun invoke(key: String, time: Long) {
        missionRepository.putCompletedTime(key, time)
    }
}