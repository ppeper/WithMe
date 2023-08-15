package com.bonobono.domain.usecase.preference

import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.mission.IsSuccess
import com.bonobono.domain.repository.MissionRepository
import javax.inject.Inject

class GetBooleanUseCase  @Inject constructor(
    private val missionRepository: MissionRepository
) {
    operator fun invoke(key: String) : Boolean {
        return missionRepository.getBoolean(key)
    }
}