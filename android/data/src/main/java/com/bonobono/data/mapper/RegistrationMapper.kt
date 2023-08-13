package com.bonobono.data.mapper

import com.bonobono.data.model.registration.request.LoginRequest
import com.bonobono.data.model.registration.request.MemberRequest
import com.bonobono.data.model.registration.request.RoleRequest
import com.bonobono.data.model.registration.response.AuthoritySetResponse
import com.bonobono.data.model.registration.response.LoginResponse
import com.bonobono.data.model.registration.response.MemberResponse
import com.bonobono.data.model.registration.response.TokenResponse
import com.bonobono.domain.model.registration.Authority
import com.bonobono.domain.model.registration.LoginInput
import com.bonobono.domain.model.registration.LoginResult
import com.bonobono.domain.model.registration.Member
import com.bonobono.domain.model.registration.Register
import com.bonobono.domain.model.registration.Role
import com.bonobono.domain.model.registration.Token

fun MemberResponse.toDomain(): Member {
    return Member(
        role = role.map { it.toDomain() },
        name = name,
        nickname = nickname,
        phoneNumber = phoneNumber,
        username = username
    )
}

fun AuthoritySetResponse.toDomain(): Authority {
    return Authority(
        role = role
    )
}

fun TokenResponse.toDomain(): Token {
    return Token(
        accessToken = accessToken,
        accessTokenExpiresIn = accessTokenExpiresIn,
        grantType = grantType,
        refreshToken = refreshToken
    )
}

fun MemberRequest.toDomain(): Register {
    return Register(
        memberId = memberId,
        name = name,
        nickname = nickname,
        password = password,
        phoneNumber = phoneNumber,
        role = role,
        username = username
    )
}

fun RoleRequest.toDomain(): Role {
    return Role(
        role = role
    )
}

fun LoginRequest.toDomain(): LoginInput {
    return LoginInput(
        fcmtoken = fcmtoken,
        password = password,
        username = username
    )
}

fun LoginResponse.toDomain(): LoginResult {
    return LoginResult(
        memberId = memberId,
        tokenDto = tokenDto.toDomain(),
        role = role.map { it.toDomain() },
    )
}

