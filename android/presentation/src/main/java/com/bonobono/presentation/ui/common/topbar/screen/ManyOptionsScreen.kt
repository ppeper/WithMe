package com.bonobono.presentation.ui.common.topbar.screen

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.common.topbar.item.ActionMenuItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

val ManyOptionsRoute = "options"
// 사용 예시 클래스
object ManyOptionsScreen : Screen {
    override val isCenterTopBar: Boolean = false
    override val route: String = ManyOptionsRoute
    override val isAppBarVisible: Boolean = true
    override val navigationIcon: Int = R.drawable.ic_back
    override val onNavigationIconClick: () -> Unit = {
        _buttons.tryEmit(AppBarIcons.NavigationIcon)
    }
    override val navigationIconContentDescription: String? = null
    override val title: String = "Many Options"

    override val actions: List<ActionMenuItem> =
        listOf(
            ActionMenuItem.IconMenuItem.AlwaysShown(
                title = "Search",
                onClick = {
                    _buttons.tryEmit(AppBarIcons.Search)
                },
                icon = R.drawable.ic_search,
                contentDescription = null,
            ),
            ActionMenuItem.IconMenuItem.AlwaysShown(
                title = "Favorite",
                onClick = {
                    _buttons.tryEmit(AppBarIcons.Favorite)
                },
                icon = R.drawable.ic_like,
                contentDescription = null,
            ),
            ActionMenuItem.NeverShown(
                title = "Settings",
                onClick = {
                    _buttons.tryEmit(AppBarIcons.Settings)
                },
            ),
            ActionMenuItem.NeverShown(
                title = "About",
                onClick = {
                    _buttons.tryEmit(AppBarIcons.About)
                },
            ),
        )

    enum class AppBarIcons {
        NavigationIcon,
        Search,
        Favorite,
        Settings,
        About,
    }

    private val _buttons = MutableSharedFlow<AppBarIcons>(extraBufferCapacity = 1)
    val buttons: Flow<AppBarIcons> = _buttons.asSharedFlow()
}

@Composable
fun ManyOptionsScreen(
    snackbarHostState: SnackbarHostState,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val scope = rememberCoroutineScope()
    fun showSnackbar(text: String) {
        scope.launch {
            snackbarHostState.showSnackbar(
                message = text
            )
        }
    }

    LaunchedEffect(key1 = Unit) {
        ManyOptionsScreen.buttons
            .onEach { button ->
                when (button) {
                    ManyOptionsScreen.AppBarIcons.NavigationIcon -> onBackClick()
                    ManyOptionsScreen.AppBarIcons.Search,
                    ManyOptionsScreen.AppBarIcons.Favorite,
                    ManyOptionsScreen.AppBarIcons.Settings,
                    ManyOptionsScreen.AppBarIcons.About -> showSnackbar(
                        "Clicked on ${button.name}"
                    )
                }
            }
            .launchIn(this)
    }
    // 리스트 뷰
}