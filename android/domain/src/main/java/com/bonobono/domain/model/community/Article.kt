package com.bonobono.domain.model.community

data class Article(
    val content: String,
    val memberId: Int,
    val title: String,
    val type: String,
    val url: String,
    val urlTitle: String
)