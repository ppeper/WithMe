package com.bonobono.data.model.chatting.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChatMessageResponse (
    val username : String,
    val msg : String,
    val imageUrl : String,
    val createdDate : String
) : Parcelable