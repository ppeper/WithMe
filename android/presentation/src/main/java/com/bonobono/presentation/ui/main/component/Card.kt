package com.bonobono.presentation.ui.main.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bonobono.domain.model.map.Campaign
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.common.button.PrimaryButton
import com.bonobono.presentation.ui.common.text.CustomTextStyle
import com.bonobono.presentation.ui.theme.White


@Composable
fun LargeSquareCardWithAnimation(source: Int, content: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(White)
    ) {
        Box(
            modifier = Modifier.padding(12.dp)
        ) {
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                LottieLoader(
                    source = source,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxWidth()
                        .height(this.maxWidth)
                        .padding(horizontal = 40.dp)
                )
            }

            Text(
                text = content,
                style = CustomTextStyle.missionGuideTextStyle
            )

            PrimaryButton(content = "미션 해결하기", modifier = Modifier.align(Alignment.BottomEnd)) {
                onClick()
            }
        }
    }
}

@Composable
fun RankingCard(profileImage: Int, nickName: String, count: Int, ranking: Int) {
    val rankingImage = listOf<Int>(
        R.drawable.ic_1st,
        R.drawable.ic_2nd,
        R.drawable.ic_3rd
    )
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 4.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.size(8.dp))
            ProfilePhoto(
                profileImage = profileImage,
                Modifier
                    .clip(CircleShape)
                    .background(White)
                    .size(80.dp)
                    .padding(8.dp)
            )
            Spacer(modifier = Modifier.size(8.dp))
            Column {
                Text(text = nickName)
                Spacer(modifier = Modifier.size(12.dp))
                Row() {
                    Image(
                        painter = painterResource(id = R.drawable.ic_stars),
                        contentDescription = "별"
                    )
                    Spacer(modifier = Modifier.size(2.dp))
                    Text(text = "${count}회 참여")
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(id = rankingImage[ranking - 1]),
                contentDescription = "메달"
            )
        }
    }
}

@Composable
fun CampaignCard(campaign: Campaign) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(White)
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Text(text = campaign.name, style = CustomTextStyle.mapTitleTextStyle)
            Spacer(modifier = Modifier.size(4.dp))
            Text(text = campaign.startDate.toString())
            Spacer(modifier = Modifier.size(4.dp))
            Row {
                val completed = if (campaign.isCompleted) "완료" else "진행중"
                val image =
                    if (campaign.isCompleted) R.drawable.ic_star_black else R.drawable.ic_stars
                Image(painter = painterResource(id = image), contentDescription = "별")
                Spacer(modifier = Modifier.size(4.dp))
                Text(text = completed, style = CustomTextStyle.gameGuideTextStyle)
                Spacer(modifier = Modifier.size(4.dp))
                Text(text = "by ${campaign.agency}", style = CustomTextStyle.mapGrayTextStyle)
            }
        }
    }
}

@Preview
@Composable
fun CardPreview() {
    RankingCard(profileImage = R.drawable.beluga_whale, nickName = "주용가리", count = 3, ranking = 1)
}