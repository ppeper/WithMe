package com.bonobono.presentation.ui.common

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.bonobono.presentation.ui.community.views.map.CircleBackButton

@Composable
fun PermissionView(
   modifier: Modifier = Modifier,
   title: String,
   navController: NavController
) {
    val context = LocalContext.current
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        CircleBackButton(
            modifier = modifier.align(Alignment.TopStart),
            navController = navController
        )
        Column(
            modifier = modifier.wrapContentSize()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            Text(
                text = title,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight(500),
                )
            )
            SubmitButton(
                modifier = modifier,
                text = "권한 설정하러 가기",
            ) {
                // 권한 설정 화면으로 이동
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                val uri = Uri.fromParts("package", context.packageName, null)
                intent.data = uri
                ContextCompat.startActivity(context, intent, null)
            }
        }
    }
}