package com.bonobono.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.community.Article
import com.bonobono.domain.usecase.community.GetArticleByIdUseCase
import com.bonobono.domain.usecase.community.GetArticleListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommunityViewModel @Inject constructor(
    private val getArticleList: GetArticleListUseCase,
    private val getArticleById: GetArticleByIdUseCase
): ViewModel() {

    private val _articleState = MutableStateFlow<NetworkResult<List<Article>>>(NetworkResult.Loading)
    private val _articleDetailState = MutableStateFlow<NetworkResult<Article>>(NetworkResult.Loading)
    val articleState: StateFlow<NetworkResult<List<Article>>>
        get() = _articleState
    val articleDetailState: StateFlow<NetworkResult<Article>>
        get() = _articleDetailState

    fun getArticleList(type: String) = viewModelScope.launch {
        _articleState.value = getArticleList.invoke(type)
    }

    fun getArticleById(type: String, articleId: Int) = viewModelScope.launch {
        _articleDetailState.emit(getArticleById.invoke(type, articleId))
    }

}