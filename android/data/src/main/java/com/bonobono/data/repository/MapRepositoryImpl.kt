package com.bonobono.data.repository

import com.bonobono.data.mapper.toDomain
import com.bonobono.data.remote.MapService
import com.bonobono.data.remote.handleApi
import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.map.Campaign
import com.bonobono.domain.model.map.CatchCharacter
import com.bonobono.domain.model.map.CatchKey
import com.bonobono.domain.model.map.Location
import com.bonobono.domain.model.map.Ranking
import com.bonobono.domain.repository.MapRepository
import javax.inject.Inject

class MapRepositoryImpl @Inject constructor(
    private val mapService: MapService
) : MapRepository {

    override suspend fun getLocations(): NetworkResult<List<Location>> {
        return handleApi { mapService.getLocations().map { it.toDomain() } }
    }

    override suspend fun getRanking(locationId: Long): NetworkResult<List<Ranking>> {
        return handleApi { mapService.getRanking(locationId = locationId).map { it.toDomain() } }
    }

    override suspend fun getCampaign(locationId: Long): NetworkResult<List<Campaign>> {
        return handleApi { mapService.getCampaign(locationId).map { it.toDomain() } }
    }

    override suspend fun getCatchCharacterList(key: CatchKey): NetworkResult<List<CatchCharacter>> {
        return handleApi { mapService.getCatchList(key).map { it.toDomain() } }
    }

    override suspend fun postCampaign(campaign: Campaign): NetworkResult<Unit> {
        return handleApi { mapService.postCampaign(campaign) }
    }
}