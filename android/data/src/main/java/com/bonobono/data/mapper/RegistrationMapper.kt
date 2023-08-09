package com.bonobono.data.mapper

import com.bonobono.data.model.registration.response.AuthoritySetResponse
import com.bonobono.data.model.registration.response.MemberResponse
import com.bonobono.data.model.registration.response.TokenResponse
import com.bonobono.domain.model.registration.AuthoritySet
import com.bonobono.domain.model.registration.Member
import com.bonobono.domain.model.registration.Token

fun MemberResponse.toDomain() : Member {
    return Member(
        authoritySet = authoritySet.map { it.toDomain() },
        name = name,
        nickname = nickname,
        phoneNumber = phoneNumber,
        username = username
    )
}

fun AuthoritySetResponse.toDomain() : AuthoritySet {
    return AuthoritySet(
        role = role
    )
}

fun TokenResponse.toDomain() : Token {
    return Token(
        accessToken = accessToken,
        accessTokenExpiresIn = accessTokenExpiresIn,
        grantType = grantType,
        refreshToken = refreshToken
    )
}