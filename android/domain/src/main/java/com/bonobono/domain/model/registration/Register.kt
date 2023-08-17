package com.bonobono.domain.model.registration

data class Register(
    val memberId: Int,
    val name: String,
    val nickname: String,
    val password: String?,
    val phoneNumber: String,
    val role: List<Any>,
    val username: String
)