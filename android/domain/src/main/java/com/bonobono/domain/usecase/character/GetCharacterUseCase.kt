package com.bonobono.domain.usecase.character

import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.character.UserCharacter
import com.bonobono.domain.repository.CharacterRepository
import javax.inject.Inject

class GetCharacterUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) {
    suspend operator fun invoke(characterId: Int): UserCharacter {
        return when (val result = characterRepository.getCharacter(characterId)) {
            is NetworkResult.Success -> result.data
            else -> UserCharacter()
        }
    }
}