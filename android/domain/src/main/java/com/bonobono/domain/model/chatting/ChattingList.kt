package com.bonobono.domain.model.chatting

data class ChattingList (
    val nickname : String,
    val roomNumber : Int,
    val profileImgName : String?,
    val profileImgUrl : String?,
    val msg : String?,
    val messageCreatedDate : String?
)