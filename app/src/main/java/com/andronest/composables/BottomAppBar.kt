package com.andronest.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.andronest.ui.theme.Primary

@Composable
fun BottomAppBar(modifier: Modifier = Modifier) {

    val navItems = listOf<NavBarItem>(
        NavBarItem(icon = Icons.Default.Home, description = "Home",route="Home"),
        NavBarItem(icon = Icons.Default.Search, description = "Search by name",route="Search")
    )

    val selectedIndex = remember {mutableIntStateOf(0)}

    NavigationBar(
        containerColor = Primary.copy(alpha = 0.6f)
    ){

        navItems.forEachIndexed { index, item ->
            NavigationBarItem(
                onClick = { selectedIndex.value = index},
                selected = index==selectedIndex.value,
                icon = {
                    Icon(
                        imageVector = navItems[index].icon,
                        contentDescription = navItems[index].description)
                }
            )
        }

    }
}

data class NavBarItem(
    val icon: ImageVector,
    val description: String,
    val route: String
)
