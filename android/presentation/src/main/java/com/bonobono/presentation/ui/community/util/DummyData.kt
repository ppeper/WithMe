package com.bonobono.presentation.ui.community.util

import com.bonobono.presentation.ui.community.views.gallery.PhotoSelected
import com.bonobono.presentation.ui.community.views.board.BoardItem
import com.bonobono.presentation.ui.community.views.comment.TestUser

object DummyData {
    val imageUrl = "https://images.unsplash.com/photo-1689852484069-3e0fe82cc7c1?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1887&q=80"
    val imageUrl_2 = "https://images.unsplash.com/photo-1690736159167-b00621eba9f6?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1887&q=80"
    val imageUrl_3 = "https://plus.unsplash.com/premium_photo-1673002094413-4c5141902505?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1170&q=80"
    val boardList = listOf(
        BoardItem(
            title = "테스트 타이틀",
            content = "내용은 다음과 같습니다.",
            comment = 3,
            like = 3,
        ),
        BoardItem(
            title = "테스트 타이틀",
            content = "내용은 다음과 같습니다.",
            images = listOf(imageUrl, imageUrl_2, imageUrl_3),
            like = 3,
        ),
        BoardItem(
            communityId = 1,
            title = "테스트 타이틀",
            content = "내용은 다음과 같습니다.",
            images = listOf(imageUrl_3),
            comment = 3,
            isProceeding = true
        ),
        BoardItem(
            title = "테스트 타이틀",
            content = "내용은 다음과 같습니다.",
            comment = 3,
            like = 3,
        ),
        BoardItem(
            title = "테스트 타이틀",
            content = "내용은 다음과 같습니다.",
            images = listOf(imageUrl_2),
            like = 3,
        ),
        BoardItem(
            communityId = 1,
            title = "테스트 타이틀",
            content = "내용은 다음과 같습니다.",
            comment = 3,
            isProceeding = true
        )
    )
    val selectedPhotos = listOf(
        PhotoSelected(imageUrl)
    )
    val commentUser = TestUser(
        profile = imageUrl,
        name = "황신운",
        comment = "댓글 테스트",
        commentList = listOf(
            TestUser(
                profile = imageUrl,
                name = "황신운",
                comment = "댓글 테스트"
            )
        )
    )
}