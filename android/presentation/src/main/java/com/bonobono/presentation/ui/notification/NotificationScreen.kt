package com.bonobono.presentation.ui.notification

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.compose.rememberNavController
import com.bonobono.domain.model.notification.Notification
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.BoardDetailNav
import com.bonobono.presentation.ui.mypage.view.EmptyListAnimation
import com.bonobono.presentation.ui.theme.Black_100
import com.bonobono.presentation.ui.theme.DividerGray
import com.bonobono.presentation.ui.theme.PrimaryBlue
import com.bonobono.presentation.ui.theme.TextGray
import com.bonobono.presentation.ui.theme.White
import com.bonobono.presentation.utils.Constants
import com.bonobono.presentation.utils.DateUtils
import com.bonobono.presentation.viewmodel.NotificationViewModel
import java.time.LocalDateTime
import java.util.Locale

@Composable
fun NotificationScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    notificationViewModel: NotificationViewModel = hiltViewModel(),
) {
    val notificationList = notificationViewModel.notificationList.collectAsStateWithLifecycle()
    var isEdit by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopContentNotification(navController = navController, isEdit = isEdit) {
                isEdit = !isEdit
            }
        }
    ) {
        if (notificationList.value.isEmpty()) {
            EmptyListAnimation()
        } else {
            LazyColumn(
                modifier = modifier.padding(it)
            ) {
                items(notificationList.value) { item ->
                    NotificationView(notification = item, isEdit = isEdit, navController = navController,
                        notificationViewModel = notificationViewModel) {
                        notificationViewModel.deleteNotification(item.id)
                    }
                }
            }
        }
    }
}

@Composable
fun NotificationView(
    modifier: Modifier = Modifier,
    navController: NavController,
    notification: Notification,
    isEdit: Boolean,
    notificationViewModel: NotificationViewModel,
    onDeleteClicked: () -> Unit
) {
    Column {
        Row(
            modifier = modifier.padding(16.dp)
        ) {
            Column(
                modifier = modifier
                    .weight(1f)
                    .clickable(
                        interactionSource = MutableInteractionSource(),
                        indication = null
                    ) {
                        navController.navigate(
                            "${BoardDetailNav.route}/${
                                notification.type.lowercase(
                                    Locale.getDefault()
                                )
                            }/${notification.articleId}"
                        ) {
                            navController.currentDestination?.let { it1 -> popUpTo(it1.id){inclusive = true} }
                        }
                        notificationViewModel.deleteNotification(notification.id)
                    },
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = titleMapper(notification.type),
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight(700),
                            color = PrimaryBlue,
                        )
                    )
                    Spacer(modifier = modifier.width(4.dp))
                    Text(
                        text = notification.title,
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = Black_100
                        )
                    )
                }
                Text(
                    text = notification.body,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight(300),
                        color = Black_100,
                    )
                )
                Text(
                    text = DateUtils.dateToString(notification.receiveTime),
                    style = TextStyle(
                        fontSize = 12.sp,
                        color = TextGray,
                    )
                )
            }
            if (isEdit) {
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
                        onClick = { onDeleteClicked() },
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_delete),
                            contentDescription = "아이콘",
                            tint = White
                        )
                    }
                }
            }
        }
        Divider(color = DividerGray)
    }
}

@Preview
@Composable
fun Preview() {
    NotificationView(
        notification = Notification(0, "FREE", "1", "게시물에 댓글이 작성되었습니다.", "안녕하세요~", LocalDateTime.now().toString()),
        isEdit = true,
        navController = rememberNavController(),
        notificationViewModel = hiltViewModel()
    ) {}
}

fun titleMapper(type: String): String {
    return when (type) {
        Constants.FREE -> "자유게시판"
        Constants.TOGETHER -> "함께게시판"
        Constants.REPORT -> "신고게시판"
        else -> "게시판"
    }
}