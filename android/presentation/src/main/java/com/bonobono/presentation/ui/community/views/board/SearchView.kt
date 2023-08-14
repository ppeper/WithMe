package com.bonobono.presentation.ui.community.views.board

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.common.LoadingView
import com.bonobono.presentation.ui.mypage.view.EmptyListAnimation
import com.bonobono.presentation.ui.theme.TextGray
import com.bonobono.presentation.viewmodel.CommunityViewModel

@Composable
fun SearchView(
    modifier: Modifier = Modifier,
    type: String,
    navController: NavController = rememberNavController()
) {
    val communityViewModel: CommunityViewModel = hiltViewModel()
    val articleList by communityViewModel.searchArticleList.collectAsStateWithLifecycle()
    val keyword by communityViewModel.keyword.collectAsStateWithLifecycle()
    val isSearching by communityViewModel.isSearching.collectAsStateWithLifecycle()
    communityViewModel.setCommunityType(type)

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    modifier = modifier.size(32.dp),
                    painter = painterResource(id = R.drawable.ic_back), contentDescription = "뒤로가기"
                )
            }
            Spacer(modifier = modifier.width(8.dp))
            OutlinedTextField(
                modifier = modifier.fillMaxWidth(),
                value = keyword,
                onValueChange = communityViewModel::onSearchTextChange,
                placeholder = { Text(text = "검색할 키워드를 입력해주세요", style = TextStyle(color = TextGray)) },
                shape = RoundedCornerShape(10.dp),
                colors = OutlinedTextFieldDefaults.colors(),
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = "검색"
                    )
                },
                trailingIcon = {
                    Icon(
                        modifier = modifier.clickable { communityViewModel.onSearchTextChange("") },
                        painter = painterResource(id = R.drawable.ic_delete),
                        contentDescription = "삭제"
                    )
                },
            )
        }
        Spacer(modifier = modifier.height(16.dp))
        if (isSearching == true) {
            LoadingView()
        } else {
            if (articleList.isEmpty()) {
                EmptyListAnimation()
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(vertical = 16.dp)
                ) {
                    items(articleList) { item ->
                        BoardItemView(type = type, article = item, navController = navController)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewSearchView() {
    SearchView(type = "free")
}