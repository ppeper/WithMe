package com.bonobono.domain.usecase.mission

import com.bonobono.domain.repository.MissionRepository
import javax.inject.Inject

class PostAttendanceUseCase @Inject constructor(
    private val missionRepository: MissionRepository,
) {
    suspend operator fun invoke(memberId: Int) {
        missionRepository.postAttendance(memberId)
    }
}