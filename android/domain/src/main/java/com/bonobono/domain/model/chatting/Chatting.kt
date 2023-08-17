package com.bonobono.domain.model.chatting

data class Chatting (
    val nickname : String,
    val roomNumber : Int,
    val profileImgName : String?,
    val profileImgUrl : String?,
    val messages : List<ChattingMessage>?
)