package com.bonobono.presentation.ui.community.views.link

import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.bonobono.domain.model.community.Link
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.theme.Black_100
import com.bonobono.presentation.ui.theme.DividerGray
import com.bonobono.presentation.ui.theme.LightGray
import com.bonobono.presentation.ui.theme.TextGray
import com.bonobono.presentation.ui.theme.White
import com.bonobono.presentation.viewmodel.CommunityViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.IOException

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LinkView(
    modifier: Modifier = Modifier,
    communityViewModel: CommunityViewModel = hiltViewModel(),
    hint: String = "같이 하고 싶은 캠페인 혹은 오픈카톡방\n링크를 추가해주세요."
) {
    var titleState by rememberSaveable { mutableStateOf("") }
    var linkState by rememberSaveable { mutableStateOf("") }
    var showSheet by remember { mutableStateOf(false) }
    var currentLink by communityViewModel.link

    Column(
        modifier = modifier.wrapContentHeight(),
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            LinkHeader()
            Spacer(modifier = modifier.weight(1f))
            IconButton(onClick = { showSheet = true }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_add),
                    contentDescription = "추가",
                    tint = TextGray
                )
            }
            val scope = rememberCoroutineScope()
            if (showSheet) {
                ModalBottomSheet(
                    onDismissRequest = { showSheet = false },
                    containerColor = White,
                    tonalElevation = 0.dp,
                    shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)
                ) {
                    LinkBottomSheetContent(
                        titleState = titleState,
                        linkState = linkState,
                        onTitleChange = { titleState = it },
                        onLinkChange = { linkState = it },
                        onCancelClick = {
                            linkState = ""
                            titleState = ""
                            showSheet = false
                        },
                        onSubmitCLick = {
                            scope.launch {
                                if (!linkState.startsWith("https://")) {
                                    linkState = "https://$linkState"
                                }
                                val link = Link(url = linkState, urlTitle = titleState)
                                currentLink = getMetaData(link)
                                linkState = ""
                                titleState = ""
                                showSheet = false
                            }
                        }
                    )
                }
            }
        }
        Divider(color = DividerGray)
        Spacer(modifier = modifier.size(16.dp))

        if (currentLink == Link()) {
            Text(
                text = hint,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight(400),
                    color = TextGray,
                )
            )
        } else {
            LinkImageTitle(link = currentLink, R.drawable.ic_delete) {
                currentLink = Link()
            }
        }
    }
}

@Composable
fun WebView(
    url: String,
) {
    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { context ->
            WebView(context).apply {
                webViewClient = WebViewClient()
                loadUrl(url)
            }
        }
    )
}

@Composable
fun LinkImageTitle(
    link: Link,
    @DrawableRes icon: Int,
    onIconClicked: () -> Unit
) {
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(1.dp, color = DividerGray),
        colors = CardDefaults.cardColors(containerColor = White),
    ) {
        Row(modifier = Modifier.padding(8.dp)
            .clickable {
                onIconClicked()
            },
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(72.dp)
                    .clip(RoundedCornerShape(10.dp)),
                model = ImageRequest.Builder(LocalContext.current)
                    .error(R.drawable.no_link_image)
                    .data(link.imageUrl)
                    .build(),
                contentDescription = "선택된 사진",
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .weight(1f)
            ) {
                Text(
                    text = link.urlTitle,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight(700),
                        color = Black_100,
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = link.content,
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight(400),
                        color = TextGray,
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .border(
                        width = 1.dp,
                        color = White,
                        shape = CircleShape
                    )
                    .clip(CircleShape)
                    .background(color = Black_100)
            ) {
                IconButton(
                    onClick = { onIconClicked() },
                ) {
                    Icon(
                        painter = painterResource(icon),
                        contentDescription = "아이콘",
                        tint = White
                    )
                }
            }
        }
    }
}

@Composable
fun LinkHeader() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_link),
            contentDescription = "링크",
            tint = TextGray
        )
        Text(
            text = "링크",
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight(400),
                color = TextGray
            )
        )
    }
}

suspend fun getMetaData(link: Link): Link {
    try {
        val doc: Document = withContext(Dispatchers.IO) {
            Jsoup.connect(link.url).get()
        }
        val title: String = link.urlTitle.ifBlank { doc.title() }
        val imageElements = doc.select("meta[property=og:image]")
        val imageUrl = if (imageElements.isNotEmpty()) {
            imageElements.first()?.attr("content") ?: ""
        } else {
            ""
        }

        val contentElements = doc.select("meta[property=og:description]")
        val content = if (contentElements.isNotEmpty()) {
            contentElements.first()?.attr("content") ?: ""
        } else {
            "내용이 없습니다."
        }

        return link.copy(
            urlTitle = link.urlTitle.ifBlank { title },
            imageUrl = imageUrl,
            content = content,
            isSuccess = true
        )
    } catch (e: IOException) {
        Log.d("TEST", "getMetaData: ")
        return Link(isSuccess = true)
    }
}

@Preview
@Composable
fun PreviewLinkHeader() {
    LinkHeader()
}

@Preview
@Composable
fun PreviewLinkView() {
    LinkView()
}

@Preview
@Composable
fun PreviewLinkImageTitle() {
    LinkImageTitle(link = Link(), R.drawable.ic_delete, onIconClicked = {})
}
