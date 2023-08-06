package com.bonobono.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.community.Article
import com.bonobono.domain.usecase.community.GetArticleByIdUseCase
import com.bonobono.domain.usecase.community.GetArticleListUseCase
import com.bonobono.domain.usecase.community.UpdateArticleLikeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommunityViewModel @Inject constructor(
    private val getArticleList: GetArticleListUseCase,
    private val getArticleById: GetArticleByIdUseCase,
    private val updateArticleLike: UpdateArticleLikeUseCase
): ViewModel() {

    private val _articleState = MutableStateFlow<NetworkResult<List<Article>>>(NetworkResult.Loading)
    val articleState = _articleState.asStateFlow()

    private val _articleDetailState = MutableStateFlow<NetworkResult<Article>>(NetworkResult.Loading)
    val articleDetailState = _articleDetailState.asStateFlow()

    private val _articleLikeState = MutableStateFlow(Unit)
    val articleLikeState = _articleLikeState.asStateFlow()

    fun getArticleList(type: String) = viewModelScope.launch {
        _articleState.emit(getArticleList.invoke(type))
    }

    fun getArticleById(type: String, articleId: Int) = viewModelScope.launch {
        _articleDetailState.emit(getArticleById.invoke(type, articleId))
    }

    fun updateArticleLike(type: String, articleId: Int) = viewModelScope.launch {
        _articleLikeState.emit(updateArticleLike.invoke(type, articleId))
    }
}