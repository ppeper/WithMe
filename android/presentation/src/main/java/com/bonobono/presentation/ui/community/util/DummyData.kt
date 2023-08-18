package com.bonobono.presentation.ui.community.util

import com.bonobono.domain.model.community.Article
import com.bonobono.domain.model.community.Comment
import com.bonobono.domain.model.community.Image
import com.bonobono.domain.model.map.Location
import com.bonobono.presentation.ui.community.views.gallery.PhotoSelected

object DummyData {
    val imageUrl =
        "https://images.unsplash.com/photo-1689852484069-3e0fe82cc7c1?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1887&q=80"
    val imageUrl_2 =
        "https://images.unsplash.com/photo-1690736159167-b00621eba9f6?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1887&q=80"
    val imageUrl_3 =
        "https://plus.unsplash.com/premium_photo-1673002094413-4c5141902505?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1170&q=80"
    val selectedPhotos = listOf(PhotoSelected(imageUrl))
    val comment1 = Comment(id = 1, content = "테스트1", parentCommentId = null)
    val comment2 = Comment(id = 2, content = "테스트3", parentCommentId = 3)
    val comment3 = Comment(id = 4, content = "테스트4", parentCommentId = null)
    val commentWithChild = Comment(id = 3, content = "테스트2", childComments = listOf(comment2, comment2), parentCommentId = null)
    val dummyArticle = Article(
        articleId = 1,
        type = "TOGETHER",
        title = "쓰레기 Article Title",
        content = "쓰레기 content Title",
        nickname = "홍길동",
        profileImg = imageUrl,
        images = listOf(Image("asd", imageUrl_2), Image("asd", imageUrl_3)),
        commentCnt = 3,
        likes = 3,
        recruitStatus = true,
        comments = listOf(
            comment1,
            commentWithChild,
            comment3
        ),
        url = "https://op.gg"
    )
    val locations = listOf(
        Location(
            id = 1,
            name = "1해변",
            centerLatitude = 0.0,
            centerLongitude = 0.0
        ),
        Location(
            id = 1,
            name = "1해변",
            centerLatitude = 0.0,
            centerLongitude = 0.0
        )
    )
}