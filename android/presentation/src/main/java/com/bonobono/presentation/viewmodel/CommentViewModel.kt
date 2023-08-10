package com.bonobono.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.community.Comment
import com.bonobono.domain.usecase.comment.UpdateCommentLikeUseCase
import com.bonobono.domain.usecase.comment.WriteCommentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommentViewModel @Inject constructor(
    private val writeComment: WriteCommentUseCase,
    private val updateCommentLike: UpdateCommentLikeUseCase
): ViewModel() {

    private val _commentId = MutableStateFlow(-1L)
    val commentId = _commentId.asStateFlow()

    private val _commentState = mutableStateOf<NetworkResult<Comment>>(NetworkResult.Loading)
    val commentState = _commentState

    private val _commentLike = MutableStateFlow(false)
    val commentLike = _commentLike.asStateFlow()

    fun setCommentId(id: Long) = viewModelScope.launch {
        _commentId.value = id
    }

    fun writeComment(type: String, articleId: Long, comment: Comment) = viewModelScope.launch {
        _commentState.value = writeComment.invoke(type, articleId, comment)
    }

    fun updateCommentLike(type: String, articleId: Long, commentId: Long) = viewModelScope.launch {
        updateCommentLike.invoke(type, articleId = articleId, commentId = commentId)
    }
}