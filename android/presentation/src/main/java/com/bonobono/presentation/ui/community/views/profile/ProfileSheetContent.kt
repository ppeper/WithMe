package com.bonobono.presentation.ui.community.views.profile

import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.bonobono.domain.model.community.Article
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.common.CancelButton
import com.bonobono.presentation.ui.common.CommonTextField
import com.bonobono.presentation.ui.common.SubmitButton
import com.bonobono.presentation.ui.community.util.DummyData
import com.bonobono.presentation.ui.community.views.link.LinkBottomSheetContent
import com.bonobono.presentation.ui.community.views.link.LinkHeader
import com.bonobono.presentation.ui.theme.Black_100
import com.bonobono.presentation.ui.theme.DividerGray
import com.bonobono.presentation.utils.rememberImeState

@Composable
fun ProfileSheetContent(
    modifier: Modifier = Modifier,
    article: Article,
    onChatClick: () -> Unit,
) {
    val imeState = rememberImeState()
    val scrollState = rememberScrollState()
    LaunchedEffect(key1 = imeState.value) {
        scrollState.animateScrollTo(scrollState.maxValue, tween(300))
    }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
            .imePadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        AsyncImage(
            modifier = modifier.size(100.dp).clip(CircleShape),
            model = ImageRequest.Builder(LocalContext.current)
                .error(R.drawable.default_profile)
                .data(article.profileImg)
                .build(),
            contentDescription = "업로드 사진",
            contentScale = ContentScale.Crop
        )
        Text(
            text = article.nickname,
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight(700),
                color = Black_100,
            )
        )
        SubmitButton(
            modifier = modifier.fillMaxWidth()
                .height(48.dp),
            text = "채팅 하기"
        ) {
            onChatClick()
        }
    }
}

@Preview
@Composable
fun PreviewLinkBottomSheet() {
    ProfileSheetContent(
        article = DummyData.dummyArticle,
        onChatClick = {}
    )
}