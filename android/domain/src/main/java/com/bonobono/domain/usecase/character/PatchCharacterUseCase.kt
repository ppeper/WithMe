package com.bonobono.domain.usecase.character

import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.character.SaveCharacter
import com.bonobono.domain.model.character.UserCharacter
import com.bonobono.domain.repository.CharacterRepository
import javax.inject.Inject

class PatchCharacterUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) {
    suspend operator fun invoke(character: SaveCharacter): Boolean {
        return when(val result = characterRepository.patchSavaCharacter(character)) {
            is NetworkResult.Success -> true
            else -> false
        }
    }
}