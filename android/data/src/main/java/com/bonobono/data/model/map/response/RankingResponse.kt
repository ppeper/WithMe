package com.bonobono.data.model.map.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RankingResponse(
    val count: Int,
    val nickname: String,
    val profileImg: String?
) : Parcelable