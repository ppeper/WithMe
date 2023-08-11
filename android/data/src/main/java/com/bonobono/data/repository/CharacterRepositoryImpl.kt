package com.bonobono.data.repository

import com.bonobono.data.mapper.toDomain
import com.bonobono.data.model.character.response.UserCharacterResponse
import com.bonobono.data.remote.CharacterService
import com.bonobono.data.remote.handleApi
import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.character.UserCharacter
import com.bonobono.domain.repository.CharacterRepository
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val characterService: CharacterService
) : CharacterRepository {
    override suspend fun getUserCharacterList(): NetworkResult<List<UserCharacter>> {
        return handleApi { characterService.getUserCharacterList(1).map { it.toDomain() } }
    }

}