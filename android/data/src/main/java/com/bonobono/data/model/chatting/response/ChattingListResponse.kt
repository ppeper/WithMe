package com.bonobono.data.model.chatting.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChattingListResponse (
    val nickname : String,
    val roomNumber : Int,
    val profileImgName : String,
    val profileImgUrl : String,
    val msg : String,
    val messageCreatedDate : String
) : Parcelable