package com.bonobono.presentation.ui.main.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bonobono.presentation.ui.common.button.PrimaryButton
import com.bonobono.presentation.ui.common.text.CustomTextStyle
import com.bonobono.presentation.ui.theme.White

@Composable
fun LargeSquareCardWithAnimation(source: Int, content: String) {
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

            }
        }
    }
}