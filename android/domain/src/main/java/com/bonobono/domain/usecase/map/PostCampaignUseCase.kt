package com.bonobono.domain.usecase.map

import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.map.Campaign
import com.bonobono.domain.repository.MapRepository
import javax.inject.Inject

class PostCampaignUseCase @Inject constructor(
    private val mapRepository: MapRepository
) {
    suspend operator fun invoke(campaign: Campaign): NetworkResult<Unit> {
        return mapRepository.postCampaign(campaign = campaign)
    }
}
