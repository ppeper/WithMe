package com.bonobono.domain.model.registration

data class Token(
    val accessToken: String,
    val accessTokenExpiresIn: Int,
    val grantType: String,
    val refreshToken: String
)