package com.bonobono.domain.repository

import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.map.Campaign
import com.bonobono.domain.model.map.CatchCharacter
import com.bonobono.domain.model.map.CatchKey
import com.bonobono.domain.model.map.Location
import com.bonobono.domain.model.map.Ranking

interface MapRepository {
    suspend fun getLocations() : NetworkResult<List<Location>>
    suspend fun getRanking(locationId: Long) : NetworkResult<List<Ranking>>
    suspend fun getCampaign(locationId: Long) : NetworkResult<List<Campaign>>
    suspend fun getCatchCharacterList(key: CatchKey) : NetworkResult<List<CatchCharacter>>
    suspend fun postCampaign(campaign: Campaign) : NetworkResult<Unit>
}