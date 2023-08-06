package com.bonobono.data.mapper

import com.bonobono.data.BuildConfig
import com.bonobono.data.model.community.request.CommentRequest
import com.bonobono.data.model.community.response.ArticleDetailResponse
import com.bonobono.data.model.community.response.ArticleResponse
import com.bonobono.data.model.community.response.CommentResponse
import com.bonobono.data.model.community.response.ImageResponse
import com.bonobono.domain.model.community.Article
import com.bonobono.domain.model.community.Comment
import com.bonobono.domain.model.community.Image
import java.util.Date

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
        profileImg = profileImg ?: "",
        recruitStatus = recruitStatus,
        url = url ?: "",
        urlTitle = urlTitle ?: "",
        createdDate = createdDate ?: Date(),
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
        profileImg = profileImg,
        childComments = childComments.map { it.toDomain() },
        liked = liked,
        likes = likes,
        createdDate = createdDate
    )
}

fun Comment.toModel(): CommentRequest {
    return CommentRequest(
        parentCommentId = parentCommentId,
        content = content
    )
}

fun ImageResponse.toDomain(): Image {
    return Image(
        imageName = imageName,
        imageUrl = BuildConfig.IMAGE_BASE_URL + imageUrl
    )
}