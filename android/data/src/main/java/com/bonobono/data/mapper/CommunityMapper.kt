package com.bonobono.data.mapper

import com.bonobono.data.BuildConfig
import com.bonobono.data.model.community.ArticleDetailResponse
import com.bonobono.data.model.community.ArticleResponse
import com.bonobono.data.model.community.CommentResponse
import com.bonobono.data.model.community.ImageResponse
import com.bonobono.domain.model.community.Article
import com.bonobono.domain.model.community.Comment
import com.bonobono.domain.model.community.Image

fun ArticleResponse.toDomain(): Article {
    return Article(
        articleId = articleId,
        type = type,
        title = title,
        content = content,
        mainImage = images?.toDomain(),
        commentCnt = comments,
        likes = likes,
        nickname = nickname,
        profileImg = profileImg,
        recruitStatus = recruitStatus,
        url = url ?: "",
        urlTitle = urlTitle ?: "",
        createdDate = createdDate,
        views = views
    )
}

fun ArticleDetailResponse.toDomain(): Article {
    return Article(
        articleId = articleId,
        type = type,
        title = title,
        content = content,
        images = images?.map { it.toDomain() } ?: emptyList(),
        commentCnt = commentCnt,
        comments = comments.map { it.toDomain() },
        likes = likes,
        nickname = nickname,
        profileImg = profileImg,
        recruitStatus = recruitStatus,
        url = url,
        urlTitle = urlTitle,
        createdDate = createdDate,
        views = views
    )
}

fun CommentResponse.toDomain(): Comment {
    return Comment(
        parentCommentId = parentCommentId,
        content = content,
        nickname = nickname,
        profileUrl = profileUrl,
        childComments = childComments.map { it.toDomain() },
        liked = liked,
        likes = likes,
        createdAt = createdAt
    )
}

fun ImageResponse.toDomain(): Image {
    return Image(
        imageName = imageName,
        imageUrl = BuildConfig.IMAGE_BASE_URL + imageUrl
    )
}