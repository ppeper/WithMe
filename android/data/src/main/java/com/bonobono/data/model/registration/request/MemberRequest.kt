package com.bonobono.data.model.registration.request

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MemberRequest(
    val memberId: Int,
    val name: String,
    val nickname: String,
    val password: String,
    val phoneNumber: String,
    val role: List<RoleRequest>,
    val username: String
) : Parcelable