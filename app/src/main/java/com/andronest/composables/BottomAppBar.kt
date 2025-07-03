package com.andronest.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.andronest.R
import com.andronest.navigation.Navigation
import com.andronest.ui.theme.Primary

@Composable
fun BottomAppBar(
    navController: NavController,
    onDiscover: () -> Unit = {},
    onHome: () -> Unit = {},
    selectedIndex: Int,
    modifier: Modifier = Modifier
) {

    val navItems = listOf(
        NavBarItem(
            iconImageVector = Icons.Default.Home,
            title = Navigation.Home.route,
            route = Navigation.Home.route
        ),
        NavBarItem(
            icon = R.drawable.baseline_bakery_dining_48,
            title = Navigation.Discover.route,
            route = Navigation.Discover.route
        ),
        NavBarItem(
            iconImageVector = Icons.Default.Search,
            title = Navigation.Search.route,
            route = Navigation.Search.route
        )
    )

    //var selectedIndex by rememberSaveable { mutableIntStateOf(0) }

    NavigationBar(
        containerColor = Primary.copy(alpha = 0.6f)
    ) {

        navItems.forEachIndexed { index, item ->
            NavigationBarItem(
                onClick = {

                    when(item.route){
                        Navigation.Discover.route -> {
                            onDiscover()
                        }
                        Navigation.Home.route -> {
                            onHome()
                        }
                    }
                },
                selected = index == selectedIndex,
                icon = {

                    if (item.icon != null) {
                        Icon(painterResource(item.icon), contentDescription = item.title)
                    } else {
                        item.iconImageVector?.let { icon ->
                            Icon(imageVector = icon, contentDescription = item.title)
                        }
                    }
                }
            )
        }
    }
}

data class NavBarItem(
    val icon: Int? = null,
    val iconImageVector: ImageVector? = null,
    val title: String? = null,
    val route: String? = null
)
