package com.bonobono.presentation.ui.game

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.common.text.CustomTextStyle
import com.bonobono.presentation.ui.game.component.CountdownTimer
import com.bonobono.presentation.ui.game.component.DragTarget
import com.bonobono.presentation.ui.game.component.DropTarget
import com.bonobono.presentation.ui.game.component.GameTextBox
import com.bonobono.presentation.ui.game.component.LongPressDraggable
import com.bonobono.presentation.ui.main.component.LottieLoader

data class Trash(
    val name: String,
    val image: Int
)

data class TrashCan(
    val name: String,
    val image: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreen() {
    val trash = Trash("계란", R.drawable.image_egg)
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        LottieLoader(source = R.raw.animation_game_background, modifier = Modifier.fillMaxSize())
        LongPressDraggable(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(alignment = Alignment.TopCenter)
            ) {
                GameTextBox(
                    name = "클로버 요정",
                    content = "분리수거를 하지 않으면 바다가 계속 아플거야!\n이 쓰레기를 버리려면 어떻게 버려야할까?",
                    modifier = Modifier
                )
                CountdownTimer(modifier = Modifier.align(alignment = Alignment.End))
            }
            TrashItemCard(trashItem = trash, Modifier.align(alignment = Alignment.Center))
            TrashCanScreen(Modifier.align(alignment = Alignment.BottomCenter))
        }
    }
}


@Composable
fun TrashItemCard(trashItem: Trash, modifier: Modifier) {
    Card(
        shape = RoundedCornerShape(24.dp),
        modifier = modifier.padding(8.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(10.dp)
        ) {
            DragTarget(modifier = Modifier.size(130.dp), dataToDrop = trashItem) {
                Image(
                    painter = painterResource(id = trashItem.image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(130.dp)
                        .clip(RoundedCornerShape(16.dp))
                )
            }
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = trashItem.name,
                style = CustomTextStyle.mapTitleTextStyle
            )
        }
    }
}

@Composable
fun TrashCanScreen(modifier: Modifier) {
    val items = listOf<TrashCan>(
        TrashCan("일반", R.drawable.trashcan),
        TrashCan("플라스틱", R.drawable.trashcan),
        TrashCan("유리", R.drawable.trashcan),
        TrashCan("캔", R.drawable.trashcan),
    )
    Row(
        modifier = modifier
            .background(
                Color.LightGray,
                shape = RoundedCornerShape(topEnd = 10.dp, topStart = 10.dp)
            )
            .fillMaxHeight(0.3f)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        items.forEach {
            TrashCanCard(it, Modifier.weight(0.2f))
        }
    }
}

@Composable
fun TrashCanCard(trashCan: TrashCan, modifier: Modifier) {
    val trashItems = remember {
        mutableStateMapOf<Int, Trash>()
    }

    DropTarget<Trash>(
        modifier = modifier
            .padding(12.dp)
    ) { isInBound, foodItem ->
        val bgColor = if (isInBound) {
            Color.Red
        } else {
            Color.White
        }

        Column(
            modifier = Modifier
                .shadow(elevation = 4.dp, shape = RoundedCornerShape(16.dp))
                .fillMaxHeight(0.8f)
                .background(
                    bgColor,
                    RoundedCornerShape(16.dp)
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = trashCan.image), contentDescription = null,
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = trashCan.name,
                fontSize = 18.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(6.dp))
        }
    }
}

