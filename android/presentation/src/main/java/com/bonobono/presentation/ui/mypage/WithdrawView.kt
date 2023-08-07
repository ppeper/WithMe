package com.bonobono.presentation.ui.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.mypage.view.WithdrawAnimation
import com.bonobono.presentation.ui.theme.LightGray
import com.bonobono.presentation.ui.theme.PrimaryBlue
import com.bonobono.presentation.ui.theme.White

@Composable
fun WithdrawView(onDismissRequest: () -> Unit) {
    Dialog(onDismissRequest = { onDismissRequest }) {
        Box (modifier = Modifier
            .fillMaxWidth()
            .background(color = White, shape = RoundedCornerShape(8.dp))
        ) {
            Column {
                Text(text = "회원탈퇴")
                Text(text = "바다 친구들을 두고 떠나실 건가요?")
                WithdrawButton()
                Spacer(modifier = Modifier.height(16.dp))
                WithdrawAnimation()
            }
        }
    }
}

@Composable
fun WithdrawButton() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .wrapContentWidth()
                .padding(0.dp, 0.dp, 4.dp, 0.dp),
            colors = ButtonDefaults.buttonColors(containerColor = LightGray)
        ) {
            Text(text = "함께 해요")
        }
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .wrapContentWidth()
                .padding(4.dp, 0.dp, 0.dp, 0.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue)
        ) {
            Text(text = "떠날래요")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun WithdrawPreview() {
    WithdrawView({})
}