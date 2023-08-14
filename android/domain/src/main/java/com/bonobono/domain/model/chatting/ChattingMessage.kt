package com.bonobono.domain.model.chatting

data class ChattingMessage (
    val username : String,
    val msg : String,
    val imageUrl : String,
    val createdDate : String
)