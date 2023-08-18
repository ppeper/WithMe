package com.bonobono.domain.model.registration

data class LoginResult(
    val memberId: Int,
    val tokenDto: Token,
    val role: List<Authority>,
)