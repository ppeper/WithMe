package com.bonobono.domain.usecase.character

import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.character.OurCharacter
import com.bonobono.domain.model.character.UserCharacter
import com.bonobono.domain.repository.CharacterRepository
import javax.inject.Inject

class GetOurCharacterListUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) {
    suspend operator fun invoke(memberId: Int): List<OurCharacter> {
        return when (val result = characterRepository.getOurCharacterList()) {
            is NetworkResult.Success -> result.data
            else -> listOf()
        }
    }
}