package com.bonobono.data.repository

import com.bonobono.data.mapper.toDomain
import com.bonobono.data.remote.MapService
import com.bonobono.data.remote.handleApi
import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.map.Location
import com.bonobono.domain.repository.MapRepository
import javax.inject.Inject

class MapRepositoryImpl @Inject constructor(
    private val mapService: MapService
) : MapRepository {

    override suspend fun getLocations(): NetworkResult<List<Location>> {
        return handleApi { mapService.getLocations().map { it.toDomain() } }
    }

}