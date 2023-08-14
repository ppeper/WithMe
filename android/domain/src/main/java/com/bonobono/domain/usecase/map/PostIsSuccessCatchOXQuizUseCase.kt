package com.bonobono.domain.usecase.map

import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.mission.IsSuccess
import com.bonobono.domain.repository.MissionRepository
import javax.inject.Inject

class PostIsSuccessCatchOXQuizUseCase @Inject constructor(
    private val missionRepository: MissionRepository,

    ) {
    suspend operator fun invoke(isSuccess: IsSuccess): Boolean {
        return when (val result = missionRepository.postCatchIsSuccessOXQuiz(isSuccess)) {
            is NetworkResult.Success -> result.data
            else -> false
        }
    }
}