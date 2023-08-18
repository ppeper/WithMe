package com.bonobono.domain.repository

import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.character.OurCharacter
import com.bonobono.domain.model.character.SaveCharacter
import com.bonobono.domain.model.character.UserCharacter

interface CharacterRepository {
    suspend fun getUserCharacterList() : NetworkResult<List<UserCharacter>>
    suspend fun getOurCharacterList() : NetworkResult<List<OurCharacter>>
    suspend fun patchSavaCharacter(character: SaveCharacter) : NetworkResult<Unit>
    suspend fun getCharacter(characterId: Int) : NetworkResult<UserCharacter>
    suspend fun patchMainCharacter(characterId: Int) : NetworkResult<Unit>
}