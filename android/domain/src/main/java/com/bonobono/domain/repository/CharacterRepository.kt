package com.bonobono.domain.repository

import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.character.UserCharacter

interface CharacterRepository {
    suspend fun getUserCharacterList() : NetworkResult<List<UserCharacter>>
}