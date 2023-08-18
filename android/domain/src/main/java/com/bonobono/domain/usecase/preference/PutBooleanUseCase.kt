package com.bonobono.domain.usecase.preference

import com.bonobono.domain.repository.MissionRepository
import javax.inject.Inject

class PutBooleanUseCase @Inject constructor(
    private val missionRepository: MissionRepository
) {
    operator fun invoke(key: String, value: Boolean) {
        missionRepository.putBoolean(key, value)
    }
}