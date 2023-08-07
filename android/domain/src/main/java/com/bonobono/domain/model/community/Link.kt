package com.bonobono.domain.model.community

data class Link(
    val url: String = "https://",
    val urlTitle: String = "올바르지 않은 URL",
    val content: String = "URL을 확인해주세요.",
    val imageUrl: String = ""
)