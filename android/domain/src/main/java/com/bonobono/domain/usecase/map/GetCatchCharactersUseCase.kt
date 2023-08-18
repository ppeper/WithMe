package com.bonobono.domain.usecase.map

import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.map.Campaign
import com.bonobono.domain.model.map.CatchCharacter
import com.bonobono.domain.model.map.CatchKey
import com.bonobono.domain.repository.MapRepository
import javax.inject.Inject

class GetCatchCharactersUseCase @Inject constructor(
    private val mapRepository: MapRepository
) {
    suspend operator fun invoke(catchKey: CatchKey): List<CatchCharacter> {
        return when (val result = mapRepository.getCatchCharacterList(catchKey)) {
            is NetworkResult.Success -> result.data
            else -> listOf()
        }
    }
}