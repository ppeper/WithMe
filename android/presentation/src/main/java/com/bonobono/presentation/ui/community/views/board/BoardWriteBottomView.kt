package com.bonobono.presentation.ui.community.views.board

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import com.bonobono.presentation.ui.CommunityFab
import com.bonobono.presentation.ui.NavigationRouteName.COMMUNITY_UPDATE_REPORT
import com.bonobono.presentation.ui.theme.DividerGray
import com.bonobono.presentation.ui.theme.TextGray

@Composable
fun BoardWriteBottomView(
    modifier: Modifier = Modifier,
    route: String,
    onPhotoClick: () -> Unit,
    onMapClick: () -> Unit
) {
    Column {
        Divider(color = DividerGray)
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconTextView(
                modifier = modifier,
                R.drawable.ic_photo,
                "사진",
                onPhotoClick
            )
            if (route == CommunityFab.REPORT.route || route.startsWith(COMMUNITY_UPDATE_REPORT)) {
                IconTextView(
                    modifier = modifier,
                    R.drawable.ic_map_pin,
                    "위치",
                    onMapClick
                )
            }
        }
    }
}

@Composable
fun IconTextView(
    modifier: Modifier = Modifier,
    icon: Int,
    text: String,
    onClickListener: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = null
            ) {
                onClickListener()
            }
    ) {
        Row(
            modifier = modifier
                .wrapContentHeight()
                .padding(start = 16.dp, top = 16.dp, bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(icon),
                contentDescription = text
            )
            Text(
                text = text,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight(400),
                    color = TextGray
                )
            )
        }
    }
}

@Preview
@Composable
fun PreviewBoardWriteBottomView() {
    BoardWriteBottomView(route = CommunityFab.REPORT.route, onPhotoClick = {}, onMapClick = {})
}
