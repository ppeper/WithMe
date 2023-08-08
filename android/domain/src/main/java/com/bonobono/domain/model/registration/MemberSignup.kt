package com.bonobono.domain.model.registration

data class MemberSignup(
    val name: String,
    val nickname: String,
    val password: String,
    val phoneNumber: String,
    val username: String
)