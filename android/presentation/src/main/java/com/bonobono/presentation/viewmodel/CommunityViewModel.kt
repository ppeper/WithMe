package com.bonobono.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.community.Article
import com.bonobono.domain.model.community.Link
import com.bonobono.domain.usecase.community.AdminCompleteUseCase
import com.bonobono.domain.usecase.community.DeleteArticleUseCase
import com.bonobono.domain.usecase.community.GetArticleByIdUseCase
import com.bonobono.domain.usecase.community.GetArticleListUseCase
import com.bonobono.domain.usecase.community.RecruitCompleteUseCase
import com.bonobono.domain.usecase.community.SearchArticleUseCase
import com.bonobono.domain.usecase.community.UpdateArticleLikeUseCase
import com.bonobono.domain.usecase.community.WriteArticleUseCase
import com.naver.maps.geometry.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommunityViewModel @Inject constructor(
    private val getArticleList: GetArticleListUseCase,
    private val getArticleById: GetArticleByIdUseCase,
    private val writeArticle: WriteArticleUseCase,
    private val updateArticleLike: UpdateArticleLikeUseCase,
    private val deleteArticle: DeleteArticleUseCase,
    private val recruitComplete: RecruitCompleteUseCase,
    private val adminComplete: AdminCompleteUseCase,
    private val searchArticle: SearchArticleUseCase
) : ViewModel() {

    private val _articleState =
        MutableStateFlow<NetworkResult<List<Article>>>(NetworkResult.Loading)
    val articleState = _articleState.asStateFlow()

    private val _articleDetailState =
        MutableStateFlow<NetworkResult<Article>>(NetworkResult.Loading)
    val articleDetailState = _articleDetailState.asStateFlow()

    private val _writeArticleState = MutableStateFlow<NetworkResult<Unit>>(NetworkResult.Loading)
    val writeArticleState = _writeArticleState.asStateFlow()

    private val _articleLikeState = MutableStateFlow<NetworkResult<Unit>>(NetworkResult.Loading)
    val articleLikeState = _articleLikeState.asStateFlow()

    private val _deleteArticleState = MutableStateFlow<NetworkResult<Unit>>(NetworkResult.Loading)
    val deleteArticleState = _deleteArticleState.asStateFlow()

    private val _recruitCompleteState = MutableStateFlow<NetworkResult<Unit>>(NetworkResult.Loading)
    val recruitCompleteState = _recruitCompleteState.asStateFlow()

    private val _adminCompleteState = MutableStateFlow<NetworkResult<Unit>>(NetworkResult.Loading)
    val adminCompleteState = _adminCompleteState.asStateFlow()

    // 글 수정 현재 내용
    private val _currentArticleDetail = mutableStateOf<Article?>(null)
    val currentArticleDetail = _currentArticleDetail

    // 링크 리스트
    private val _link = mutableStateOf(Link())
    val link = _link

    // 신고 위도 경도
    private val _mapState = mutableStateOf<LatLng?>(null)
    val mapState = _mapState

    private val _type = mutableStateOf("")
    val type = _type

    private val _isSearching = MutableStateFlow<Boolean?>(null)
    val isSearching = _isSearching.asStateFlow()

    private val _keyword = MutableStateFlow("")
    val keyword = _keyword.asStateFlow()

    fun onSearchTextChange(text: String) {
        _keyword.value = text
    }

    fun setCommunityType(type: String) {
        _type.value = type
    }

    // 게시글 검색
    private val _searchArticleList = MutableStateFlow<List<Article>>(emptyList())
    @OptIn(FlowPreview::class)
    val searchArticleList = _keyword
        .onEach {
            Log.d("TEST", "게시글 찾아보기: TRUE")
            _isSearching.update { true }
        }
        .debounce(100L)
        .combine(_searchArticleList) { keyword, list ->
            if (keyword.isBlank()) {
                Log.d("TEST", "게시글 찾아보기: FALSE")
                _isSearching.update { false }
                list
            } else {
                _isSearching.update { false }
                Log.d("TEST", "게시글 찾아보기: FALSE")
                searchArticle.invoke(_type.value, keyword)
            }
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(2000),
            _searchArticleList.value
        )


    fun setCurrentArticle(article: Article) {
        _currentArticleDetail.value = article
    }

    fun setCurrentLink(link: Link) {
        _link.value = link
    }

    fun setMapPosition(latLng: LatLng) {
        _mapState.value = latLng
    }

    fun removeMapPosition() {
        _mapState.value = null
    }

    fun getArticleList(type: String) = viewModelScope.launch {
        _articleState.emit(getArticleList.invoke(type))
    }

    fun getArticleById(type: String, articleId: Long) = viewModelScope.launch {
        _articleDetailState.emit(getArticleById.invoke(type, articleId))
    }

    fun writeArticle(type: String, images: List<String>?, article: Article) =
        viewModelScope.launch {
            _writeArticleState.emit(writeArticle.invoke(type, images, article))
        }

    fun updateArticleLike(type: String, articleId: Long) = viewModelScope.launch {
        _articleLikeState.emit(updateArticleLike.invoke(type, articleId))
    }

    fun deleteArticle(type: String, articleId: Long) = viewModelScope.launch {
        _deleteArticleState.emit(deleteArticle.invoke(type, articleId))
    }

    fun recruitComplete(type: String, articleId: Long) = viewModelScope.launch {
        _recruitCompleteState.emit(recruitComplete.invoke(type, articleId))
    }

    fun adminComplete(articleId: Long) = viewModelScope.launch {
        _recruitCompleteState.emit(adminComplete.invoke(articleId))
    }
}