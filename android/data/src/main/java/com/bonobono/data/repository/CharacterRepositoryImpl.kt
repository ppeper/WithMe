package com.bonobono.data.repository

import com.bonobono.data.mapper.toDomain
import com.bonobono.data.model.character.request.MainCharacterRequest
import com.bonobono.data.remote.CharacterService
import com.bonobono.data.remote.handleApi
import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.character.OurCharacter
import com.bonobono.domain.model.character.SaveCharacter
import com.bonobono.domain.model.character.UserCharacter
import com.bonobono.domain.repository.CharacterRepository
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val characterService: CharacterService
) : CharacterRepository {
    override suspend fun getUserCharacterList(): NetworkResult<List<UserCharacter>> {
        return handleApi { characterService.getUserCharacterList(1).map { it.toDomain() } }
    }

    override suspend fun getOurCharacterList(): NetworkResult<List<OurCharacter>> {
        return handleApi { characterService.getOurCharacterList(1).map { it.toDomain() } }
    }

    override suspend fun patchSavaCharacter(character: SaveCharacter): NetworkResult<Unit> {
        return handleApi { characterService.patchSaveCharacter(character) }
    }

    override suspend fun getCharacter(characterId: Int): NetworkResult<UserCharacter> {
        return handleApi { characterService.getCharacter(characterId).toDomain() }
    }

    override suspend fun patchMainCharacter(characterId: Int) : NetworkResult<Unit> {
        return handleApi { characterService.patchMainCharacter(characterId, MainCharacterRequest(true)) }
    }
}