package com.bonobono.data.model.registration.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginResponse(
    val memberId: Int,
    val tokenDto: TokenResponse,
    val role: List<AuthoritySetResponse>,
) : Parcelable