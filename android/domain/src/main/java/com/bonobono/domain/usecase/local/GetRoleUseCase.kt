package com.bonobono.domain.usecase.local

import com.bonobono.domain.repository.SharedLocalRepository
import javax.inject.Inject

class GetRoleUseCase @Inject constructor(
    private val localRepository: SharedLocalRepository
) {
    fun execute(key: String): String? = localRepository.getRole(key)
}