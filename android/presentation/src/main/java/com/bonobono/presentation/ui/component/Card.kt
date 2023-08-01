package com.bonobono.presentation.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.theme.Black_100

@Composable
fun MissionCard() {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 12.dp, horizontal = 8.dp)
        ) {
            Image(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.White)
                    .padding(12.dp),
                painter = painterResource(id = R.drawable.beluga_whale),
                contentDescription = "미션 캐릭터",
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.size(16.dp))
            Column {
                Text(
                    text = "퀴즈 풀고 경험치 받기",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Black_100
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = "Exp.5",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Black_100
                )
            }
        }
    }
}

@Composable
fun EncyclopediaCard() {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 8.dp)
        ) {
            Image(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.White)
                    .padding(12.dp),
                painter = painterResource(id = R.drawable.beluga_whale),
                contentDescription = "미션 캐릭터",
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.size(16.dp))
            Column {
                HeaderTwoText(text = "벨루가")
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = "구한 날짜: 2023.07.31",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Black_100
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = "장소: 일산지",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Black_100
                )
            }
        }
    }
}

@Composable
fun MyPageInfoCard(seaAnimalExp: String, rewardMoney: String) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            Arrangement.Center,
            Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.padding(0.dp, 0.dp, 32.dp, 0.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "바다 친구 포인트",
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "$seaAnimalExp Exp",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Divider(
                color = Color.LightGray,
                modifier = Modifier
                    .height(64.dp)
                    .width(2.dp)
            )
            Column(
                modifier = Modifier.padding(32.dp, 0.dp, 0.dp, 0.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "리워드 MONEY",
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "$rewardMoney P",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview
@Composable
fun CardPreview() {
    EncyclopediaCard()
}