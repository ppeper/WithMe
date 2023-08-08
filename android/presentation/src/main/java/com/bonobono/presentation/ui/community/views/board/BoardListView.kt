package com.bonobono.presentation.ui.community.views.board

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.NavigationRouteName
import com.bonobono.presentation.ui.theme.Black_100
import com.bonobono.presentation.ui.theme.TextGray
import com.bonobono.presentation.ui.theme.White

data class BoardInfo(
    val route: String,
    val icon: Int,
    val title: String,
    val description: String
)
@Composable
fun BoardListView(
    navController: NavController
) {
    val boardList = listOf(
        BoardInfo(
            route = NavigationRouteName.COMMUNITY_FREE,
            icon = R.drawable.ic_board_free,
            title = stringResource(R.string.community_free_title),
            description = stringResource(R.string.community_free_description)
        ),
        BoardInfo(
            route = NavigationRouteName.COMMUNITY_WITH,
            icon = R.drawable.ic_board_with,
            title = stringResource(R.string.community_with_title),
            description = stringResource(R.string.community_with_description)
        ),
        BoardInfo(
            route = NavigationRouteName.COMMUNITY_REPORT,
            icon = R.drawable.ic_board_question,
            title = stringResource(R.string.community_alert_title),
            description = stringResource(R.string.community_alert_description)
        )
    )

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp),
    ) {
        items(boardList) { item ->
            BoardView(
                item = item,
                navController = navController
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BoardView(
    modifier: Modifier = Modifier,
    item: BoardInfo,
    navController: NavController
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp),
        onClick = { navController.navigate(item.route) }
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(128.dp)
                .background(White)
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Image(
                painter = painterResource(id = item.icon),
                contentDescription = item.title,
                modifier = modifier.size(48.dp)
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = item.title,
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight(400),
                        color = Black_100,
                    )
                )
                Text(
                    text = item.description,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight(400),
                        color = TextGray,
                    )
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewCommunityListView() {
    BoardListView(navController = rememberNavController())
}