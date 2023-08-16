package com.bonobono.data.model.chatting.request

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChattingRoomRequest (
    val nickname : String
) : Parcelable