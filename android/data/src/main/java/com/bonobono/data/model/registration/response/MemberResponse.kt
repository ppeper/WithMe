package com.bonobono.data.model.registration.response

import android.os.Parcelable
import com.bonobono.domain.model.registration.Password
import kotlinx.parcelize.Parcelize

@Parcelize
data class MemberResponse(
    val authoritySet: List<AuthoritySetResponse>,
    val name: String,
    val nickname: String,
    val phoneNumber: String,
    val username: String
) : Parcelable