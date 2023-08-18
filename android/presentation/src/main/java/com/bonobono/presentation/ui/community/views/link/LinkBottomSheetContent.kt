package com.bonobono.presentation.ui.community.views.link

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bonobono.presentation.ui.common.CancelButton
import com.bonobono.presentation.ui.common.CommonTextField
import com.bonobono.presentation.ui.common.SubmitButton
import com.bonobono.presentation.ui.theme.Black_100
import com.bonobono.presentation.ui.theme.DividerGray
import com.bonobono.presentation.utils.rememberImeState

@Composable
fun LinkBottomSheetContent(
    modifier: Modifier = Modifier,
    titleState: String,
    linkState: String,
    onTitleChange: (String) -> Unit,
    onLinkChange: (String) -> Unit,
    onCancelClick: () -> Unit,
    onSubmitCLick: () -> Unit,
) {
    val imeState = rememberImeState()
    val scrollState = rememberScrollState()
    LaunchedEffect(key1 = imeState.value) {
        scrollState.animateScrollTo(scrollState.maxValue, tween(300))
    }
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
            .imePadding()
            .verticalScroll(scrollState)
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .imePadding(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            CommonTextField(
                text = titleState,
                textStyle = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight(700),
                    color = Black_100
                ),
                singleLine = true,
                hint = "링크 제목",
                onValueChange = onTitleChange,
                onFocusChange = {}
            )
            Spacer(modifier = modifier.size(8.dp))
            LinkHeader()
            Divider(color = DividerGray)
            CommonTextField(
                text = linkState,
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight(400),
                    color = Black_100
                ),
                singleLine = true,
                hint = "링크 주소를 입력해주세요",
                onValueChange = onLinkChange,
                onFocusChange = {}
            )
            Spacer(modifier = modifier.size(32.dp))
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                CancelButton(modifier = modifier.fillMaxSize().weight(1f), text = "취소") {
                    onCancelClick()
                }
                Spacer(modifier = modifier.size(8.dp))
                SubmitButton(modifier = modifier.fillMaxSize().weight(1f), text = "확인") {
                    onSubmitCLick()
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewLinkBottomSheet() {
    LinkBottomSheetContent(
        titleState = "",
        linkState = "",
        onLinkChange = { "" },
        onTitleChange = { "" },
        onCancelClick = { },
        onSubmitCLick = { },
    )
}