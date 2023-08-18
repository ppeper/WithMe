package com.bonobono.domain.usecase.map

import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.map.Location
import com.bonobono.domain.model.map.Ranking
import com.bonobono.domain.repository.MapRepository
import javax.inject.Inject

class GetRankingUseCase @Inject constructor(
    private val mapRepository: MapRepository
) {
    suspend operator fun invoke(locationId: Long): List<Ranking> {
        return when (val result = mapRepository.getRanking(locationId)) {
            is NetworkResult.Success -> result.data
            else -> listOf()
        }
    }
}