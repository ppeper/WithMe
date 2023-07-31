package com.bonobono.domain.model.community

import java.util.Date

sealed interface PostItem {
    val type: String
    val title: String
    val nickName: String
    val content: String
    val likes: Int
    val views: Int
    val createdTime: Date
    val updatedTime: Date

    // 자유 게시판
    data class Free(
        override val type: String,
        override val title: String,
        override val nickName: String,
        override val content: String,
        override val likes: Int,
        override val views: Int,
        override val createdTime: Date,
        override val updatedTime: Date
    ): PostItem

    // 한께 게시판
    data class With(
        override val type: String,
        override val title: String,
        override val nickName: String,
        override val content: String,
        override val likes: Int,
        override val views: Int,
        // 함께 게시판
        val urlTitle: String,
        val url: String,
        val recruitStatus: Boolean,
        override val createdTime: Date,
        override val updatedTime: Date,
    ): PostItem

    // 신고 게시판
    data class Report(
        override val type: String,
        override val title: String,
        override val nickName: String,
        override val content: String,
        override val likes: Int,
        override val views: Int,
        // 신고 게시판
        val latitude: Double,
        val longitude: Double,
        val adminConfirm: Boolean,
        override val createdTime: Date,
        override val updatedTime: Date
    ): PostItem
}