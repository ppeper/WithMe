package com.bonobono.data.model.registration.response

import android.os.Parcelable
import com.bonobono.data.model.registration.request.MemberRequest
import com.bonobono.domain.model.registration.Member
import kotlinx.parcelize.Parcelize

@Parcelize
data class MemberProfileResponse (
    val member: MemberRequest,
    val experience : Int
) : Parcelable