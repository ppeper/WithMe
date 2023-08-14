package com.bonobono.domain.repository

import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.character.OurCharacter
import com.bonobono.domain.model.character.SaveCharacter
import com.bonobono.domain.model.character.UserCharacter
import com.bonobono.domain.model.map.Campaign
import com.bonobono.domain.model.map.Ranking

interface CharacterRepository {
    suspend fun getUserCharacterList() : NetworkResult<List<UserCharacter>>
    suspend fun getOurCharacterList() : NetworkResult<List<OurCharacter>>
    suspend fun patchSavaCharacter(character: SaveCharacter) : NetworkResult<Unit>
}