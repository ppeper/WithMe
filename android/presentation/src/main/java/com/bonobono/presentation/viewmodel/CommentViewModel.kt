package com.bonobono.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.community.Comment
import com.bonobono.domain.usecase.comment.WriteCommentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommentViewModel @Inject constructor(
    private val writeComment: WriteCommentUseCase
): ViewModel() {

    private val _commentState = MutableStateFlow<NetworkResult<Comment>>(NetworkResult.Loading)
    val commentState = _commentState.asStateFlow()

    fun writeComment(type: String, articleId: Int, comment: Comment) = viewModelScope.launch {
        _commentState.value = NetworkResult.Loading
        _commentState.emit(writeComment.invoke(type, articleId, comment))
    }
}