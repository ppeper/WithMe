package com.bonobono.presentation.ui.community.views.link

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.theme.DividerGray
import com.bonobono.presentation.ui.theme.TextGray
import com.bonobono.presentation.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LinkView(
    modifier: Modifier = Modifier
) {
    var titleState by rememberSaveable { mutableStateOf("") }
    var linkState by rememberSaveable { mutableStateOf("") }
    var showSheet by remember  { mutableStateOf(false) }

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
            if (showSheet) {
                ModalBottomSheet(
                    onDismissRequest = { showSheet = false },
                    containerColor = White,
                    shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)
                ) {
                    LinkBottomSheetContent(
                        titleState = titleState,
                        linkState = linkState,
                        onTitleChange = { titleState = it },
                        onLinkChange = { linkState = it },
                        onCancelClick = { showSheet = false },
                        onSubmitCLick = { /* TODO("링크 작성") */}
                    )
                }
            }
        }
        Divider(color = DividerGray)
        Spacer(modifier = modifier.size(16.dp))
        // TODO("링크의 유무에 따라 보여준다")
        Text(
            text = "같이 하고 싶은 캠페인 혹은 오픈카톡방\n링크를 추가해주세요.",
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight(400),
                color = TextGray,
            )
        )
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