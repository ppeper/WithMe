package com.bonobono.domain.usecase.character

import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.character.SaveCharacter
import com.bonobono.domain.repository.CharacterRepository
import javax.inject.Inject

class PatchMainCharacterUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) {
    suspend operator fun invoke(characterId: Int): Boolean {
        return when(val result = characterRepository.patchMainCharacter(characterId)) {
            is NetworkResult.Success -> true
            else -> false
        }
    }
}