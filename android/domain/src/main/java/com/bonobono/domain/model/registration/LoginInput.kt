package com.bonobono.domain.model.registration

data class LoginInput(
    val fcmtoken: String,
    val password: String,
    val username: String
)