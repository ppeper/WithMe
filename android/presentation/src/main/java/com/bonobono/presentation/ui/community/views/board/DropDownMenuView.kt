package com.bonobono.presentation.ui.community.views.board

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.bonobono.domain.model.community.Article
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.theme.PrimaryBlue
import com.bonobono.presentation.ui.theme.White
import com.bonobono.presentation.utils.Constants

@Composable
fun DropDownMenuView(
    role: String?,
    memberId: Long,
    article: Article,
    onDeleteClick: () -> Unit,
    onFinishClick: () -> Unit,
) {
    var menuExpanded by remember {
        mutableStateOf(false)
    }
    IconButton(onClick = { menuExpanded = true }) {
        Icon(
            painter = painterResource(id = R.drawable.ic_more),
            contentDescription = "더보기",
        )
        // 더보기 아이템 리스트
        DropdownMenu(
            expanded = menuExpanded,
            onDismissRequest = { menuExpanded = false },
            modifier = Modifier.background(color = White)
        ) {
            // 글쓴이만 수정 삭제 가능
            if (memberId == article.memberId || role == Constants.ADMIN_ROLE) {
                DropdownMenuItem(
                    text = {
                        Text(text = "삭제 하기")
                    },
                    onClick = { onDeleteClick() }
                )
                if (article.type == Constants.TOGETHER && article.recruitStatus == false) {
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = "모집 마감",
                                style = TextStyle(
                                    color = PrimaryBlue,
                                    fontWeight = FontWeight(700)
                                )
                            )
                        },
                        onClick = {
                            onFinishClick()
                            menuExpanded = false
                        }
                    )
                }
                // 관리자만 답변 완료 할 수 있음
                if (role == Constants.ADMIN_ROLE) {
                    if (article.adminConfirmStatus == false) {
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = "답변 완료",
                                    style = TextStyle(
                                        color = PrimaryBlue,
                                        fontWeight = FontWeight(700)
                                    )
                                )
                            },
                            onClick = {
                                onFinishClick()
                                menuExpanded = false
                            }
                        )
                    }
                }
            }
        }
    }
}