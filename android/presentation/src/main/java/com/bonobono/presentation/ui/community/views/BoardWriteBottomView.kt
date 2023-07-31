package com.bonobono.presentation.ui.community.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.theme.DividerGray
import com.bonobono.presentation.ui.theme.TextGray

@Composable
fun BoardWriteBottomView(
    modifier: Modifier = Modifier,
    onPhotoClick: () -> Unit
) {
    Column {
        Divider(color = DividerGray)
        Box(
            modifier = modifier
                .clip(CircleShape)
                .clickable { }
        ) {
            Row(
                modifier = modifier
                    .wrapContentHeight()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_photo),
                    contentDescription = "사진"
                )
                Text(
                    text = "사진",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight(400),
                        color = TextGray
                    )
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewBoardWriteBottomView() {
    BoardWriteBottomView(onPhotoClick = {})
}
