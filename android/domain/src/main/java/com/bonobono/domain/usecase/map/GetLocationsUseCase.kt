package com.bonobono.domain.usecase.map

import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.map.Location
import com.bonobono.domain.repository.MapRepository
import javax.inject.Inject

class GetLocationsUseCase @Inject constructor(
    private val mapRepository: MapRepository
) {
    suspend operator fun invoke(): List<Location> {
        return when (val result = mapRepository.getLocations()) {
            is NetworkResult.Success -> result.data
            else -> listOf()
        }
    }
}