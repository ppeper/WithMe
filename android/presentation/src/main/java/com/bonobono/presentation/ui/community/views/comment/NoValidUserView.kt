package com.bonobono.presentation.ui.community.views.comment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.theme.BackgroundLightGray
import com.bonobono.presentation.ui.theme.DividerGray
import com.bonobono.presentation.ui.theme.TextGray

@Composable
fun NoValidUserView(
    modifier: Modifier = Modifier
) {
    Column {
        Divider(color = DividerGray)
        Row(
            modifier = modifier
                .wrapContentHeight()
                .padding(vertical = 8.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = modifier
                    .weight(1f)
                    .height(44.dp)
                    .clip(RoundedCornerShape(size = 12.dp))
                    .background(color = BackgroundLightGray)
                    .padding(vertical = 4.dp, horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_info),
                    contentDescription = "위험",
                    tint = TextGray
                )
                Spacer(modifier = modifier.size(8.dp))
                Text(text = "관리자만 댓글 작성이 가능합니다.",
                    style = TextStyle(color = TextGray)
                )
            }
        }
    }
}

@Composable
@Preview
fun PreviewNoValidUserView() {
    NoValidUserView()
}