package com.bonobono.presentation.ui.common.topbar.item

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Badge
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bonobono.presentation.R

// 공통으로 사용할 TopBar Menu Item
sealed interface ActionMenuItem {
    val title: String
    val onClick: () -> Unit

    // 1
    sealed interface IconMenuItem : ActionMenuItem {
        @get:DrawableRes
        val icon: Int
        val contentDescription: String?

        // 2
        data class AlwaysShown(
            override val title: String,
            override val contentDescription: String?,
            override val onClick: () -> Unit,
            override val icon: Int,
        ) : IconMenuItem

        data class ShownIfRoom(
            override val title: String,
            override val contentDescription: String?,
            override val onClick: () -> Unit,
            override val icon: Int,
        ) : IconMenuItem
    }

    data class NeverShown(
        override val title: String,
        override val onClick: () -> Unit,
    ) : ActionMenuItem
}

// 1
private data class MenuItems(
    val alwaysShownItems: List<ActionMenuItem.IconMenuItem>,
    val overflowItems: List<ActionMenuItem>,
)

// 2
private fun splitMenuItems(
    items: List<ActionMenuItem>,
    maxVisibleItems: Int,
): MenuItems {
    // 3
    val alwaysShownItems: MutableList<ActionMenuItem.IconMenuItem> =
        items.filterIsInstance<ActionMenuItem.IconMenuItem.AlwaysShown>().toMutableList()
    val ifRoomItems: MutableList<ActionMenuItem.IconMenuItem> =
        items.filterIsInstance<ActionMenuItem.IconMenuItem.ShownIfRoom>().toMutableList()
    val overflowItems = items.filterIsInstance<ActionMenuItem.NeverShown>()

    // 4
    val hasOverflow = overflowItems.isNotEmpty() ||
            (alwaysShownItems.size + ifRoomItems.size - 1) > maxVisibleItems
    // 5
    val usedSlots = alwaysShownItems.size + (if (hasOverflow) 1 else 0)
    // 6
    val availableSlots = maxVisibleItems - usedSlots
    // 7
    if (availableSlots > 0 && ifRoomItems.isNotEmpty()) {
        // 8
        val visible = ifRoomItems.subList(0, availableSlots.coerceAtMost(ifRoomItems.size))
        alwaysShownItems.addAll(visible)
        ifRoomItems.removeAll(visible)
    }

    // 9
    return MenuItems(
        alwaysShownItems = alwaysShownItems,
        overflowItems = ifRoomItems + overflowItems,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActionsMenu(
    items: List<ActionMenuItem>,
    isOpen: Boolean,
    onToggleOverflow: () -> Unit,
    maxVisibleItems: Int,
    notificationCount: Int = 0
) {
    // 2
    val menuItems = remember(
        key1 = items,
        key2 = maxVisibleItems,
    ) {
        splitMenuItems(items, maxVisibleItems)
    }

    menuItems.alwaysShownItems.forEach { item ->
        // 알람 Action Icon은 B
        if (item.icon == R.drawable.ic_alarm) {
            Box(modifier = Modifier.wrapContentSize()) {
                IconButton(onClick = item.onClick) {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.contentDescription
                    )
                }
                // 뱃지에 대한 색상
                if (notificationCount > 0) {
                    Badge(
                        modifier = Modifier.align(Alignment.TopEnd)
                            .padding(top = 8.dp, end = 8.dp)
                    ) {
                        if (notificationCount > 9) {
                            Text(text = "9+")
                        } else {
                            Text(text = notificationCount.toString())
                        }
                    }
                }
            }
        } else {
            IconButton(onClick = item.onClick) {
                Icon(
                    painter = painterResource(id = item.icon),
                    contentDescription = item.contentDescription,
                )
            }
        }
    }

    if (menuItems.overflowItems.isNotEmpty()) {
        // 5
        IconButton(onClick = onToggleOverflow) {
            Icon(
                painter = painterResource(id = R.drawable.ic_more),
                contentDescription = "더보기",
            )
        }
        // 더보기 아이템 리스트
        DropdownMenu(
            expanded = isOpen,
            onDismissRequest = onToggleOverflow,
        ) {
            menuItems.overflowItems.forEach { item ->
                DropdownMenuItem(
                    text = {
                        Text(item.title)
                    },
                    onClick = item.onClick
                )
            }
        }
    }
}