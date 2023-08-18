package com.bonobono.presentation.ui.mypage.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bonobono.presentation.ui.theme.ChatLightGray
import com.bonobono.presentation.ui.theme.LightGray
import com.bonobono.presentation.utils.NumberUtils

@Composable
fun MyPageInfoCard(seaAnimalExp: Int, rewardMoney: Int) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = ChatLightGray
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 0.dp, 16.dp, 16.dp )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.padding(end = 40.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "바다 친구 포인트",
                    style = TextStyle(
                        fontSize = 12.sp
                    )
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(text = NumberUtils.makeCommaExp(seaAnimalExp),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                )
            }
            Divider(
                color = LightGray,
                modifier = Modifier
                    .height(64.dp)
                    .width(2.dp)
            )
            Column(
                modifier = Modifier.padding(start = 40.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "리워드 MONEY",
                    style = TextStyle(
                        fontSize = 12.sp
                    ))
                Spacer(modifier = Modifier.height(12.dp))
                Text(text = NumberUtils.makeCommaP(rewardMoney),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    ))
            }
        }
    }
}
