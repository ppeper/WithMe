package com.bonobono.domain.model.community

data class Link(
    val url: String = "https://",
    val urlTitle: String = "타이틀이 없습니다.",
    val content: String = "내용이 없습니다.",
    val imageUrl: String = "",
    val isSuccess: Boolean = false
)