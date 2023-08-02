package com.bonobono.presentation.ui.mypage

import android.graphics.ImageDecoder
import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil.ImageLoader
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.theme.LightGray
import com.bonobono.presentation.ui.theme.PrimaryBlue

@Composable
fun WithdrawView(visible: Boolean, onDismissRequest : () -> Unit) {
    if(visible) {
        Dialog(onDismissRequest = { onDismissRequest }) {
            Box(modifier = Modifier
                .wrapContentHeight()
                .aspectRatio(1f)) {
                Column {
                    Text(text = "회원탈퇴")
                    Text(text = "바다 친구들을 두고 떠나실 건가요?")
                    WithdrawButton()
                    WithdrawAnimation()
                }
            }
        }
    }
}

@Composable
fun WithdrawButton() {
    Row (modifier =  Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically){
        Button(onClick = { /*TODO*/ },
            modifier = Modifier
                .wrapContentWidth()
                .padding(0.dp, 0.dp, 4.dp, 0.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = LightGray)) {
            Text(text = "함께 해요")
        }
        Button(onClick = { /*TODO*/ },
            modifier = Modifier
                .wrapContentWidth()
                .padding(4.dp, 0.dp, 0.dp, 0.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = PrimaryBlue)) {
            Text(text = "떠날래요")
        }
    }
}

@Composable
fun WithdrawAnimation() {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.dolphin_sad_withdraw))
    val progress by animateLottieCompositionAsState(composition = composition,
        iterations = LottieConstants.IterateForever)
    Box(modifier = Modifier.fillMaxWidth()) {
        LottieAnimation(composition = composition,
            progress = {progress},
            contentScale = ContentScale.FillHeight)
    }
}

//@Preview(showBackground = true)
//@Composable
//fun WithdrawPreview() {
//    WithdrawView(true, ())
//}