package com.bonobono.domain.repository

import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.map.Location

interface MapRepository {
    suspend fun getLocations() : NetworkResult<List<Location>>
}