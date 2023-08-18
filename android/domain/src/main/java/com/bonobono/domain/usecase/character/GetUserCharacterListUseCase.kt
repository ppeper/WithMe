package com.bonobono.domain.usecase.character

import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.character.UserCharacter
import com.bonobono.domain.model.mission.Mission
import com.bonobono.domain.repository.CharacterRepository
import javax.inject.Inject

class GetUserCharacterListUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) {
    suspend operator fun invoke(memberId: Int): List<UserCharacter> {
        return when(val result = characterRepository.getUserCharacterList()) {
            is NetworkResult.Success -> result.data
            else -> listOf()
        }
    }
}