package com.bonobono.domain.usecase.map

import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.map.Campaign
import com.bonobono.domain.model.map.Location
import com.bonobono.domain.repository.MapRepository
import javax.inject.Inject

class GetCampaignUseCase @Inject constructor(
    private val mapRepository: MapRepository
) {
    suspend operator fun invoke(locationId: Long): List<Campaign> {
        return when (val result = mapRepository.getCampaign(locationId)) {
            is NetworkResult.Success -> result.data
            else -> listOf()
        }
    }
}