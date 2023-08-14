package com.bonobono.domain.usecase.local

import com.bonobono.domain.repository.SharedLocalRepository
import javax.inject.Inject

class GetMemberIdUseCase @Inject constructor(
    private val localRepository: SharedLocalRepository
) {
    fun execute(key: String): Int = localRepository.getMemberId(key)
}