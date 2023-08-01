package com.bonobono.presentation.ui.community.views

object DummyData {
    val imageUrl = "https://images.unsplash.com/photo-1689852484069-3e0fe82cc7c1?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1887&q=80"
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
            images = listOf("test", "test", "test"),
            like = 3,
        ),
        BoardItem(
            communityId = 1,
            title = "테스트 타이틀",
            content = "내용은 다음과 같습니다.",
            images = listOf("test"),
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
            images = listOf("test", "test", "test"),
            like = 3,
        ),
        BoardItem(
            communityId = 1,
            title = "테스트 타이틀",
            content = "내용은 다음과 같습니다.",
            images = listOf("test"),
            comment = 3,
            isProceeding = true
        )
    )
    val selectedPhotos = listOf(
        PhotoSelected(imageUrl)
    )
}