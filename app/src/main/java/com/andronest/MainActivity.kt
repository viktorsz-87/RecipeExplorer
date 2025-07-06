package com.andronest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.andronest.navigation.Navigation
import com.andronest.screens.discover.DiscoverScreen
import com.andronest.screens.favorites.FavoritesScreen
import com.andronest.screens.home.HomeScreen
import com.andronest.ui.theme.RecipeExplorerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {

            RecipeExplorerTheme {

                val navController = rememberNavController()
                val currentBackStackEntry by navController.currentBackStackEntryAsState()

                // Derived state of: Runs logic only on state change
                val selectedScreen = remember(currentBackStackEntry){
                    derivedStateOf {
                        currentBackStackEntry?.destination?.route
                    }
                }.value

                NavHost(
                    navController,
                    startDestination = Navigation.Home.route
                ) {

                    composable(Navigation.Home.route) {
                        HomeScreen(
                            navController,
                            onDiscover = {
                                navController.navigate(Navigation.Discover.route)
                            },
                            selectedScreen=selectedScreen,
                            onFavorites = {
                                navController.navigate(Navigation.Favorites.route)
                            }
                        )
                    }
                    composable(
                        Navigation.Discover.route,
                        arguments = listOf(
                            navArgument("mealId"){
                                type = NavType.StringType
                                nullable = true
                                defaultValue = null
                            }
                        )
                    ) {

                        val mealId = it.arguments?.getString("mealId")

                        DiscoverScreen(
                            mealId = mealId,
                            navController = navController,
                            onHome = {
                                navController.navigate(Navigation.Home.route)
                            },
                            onFavorites = {
                                navController.navigate(Navigation.Favorites.route)
                            },
                            selectedScreen = selectedScreen,
                        )
                    }
                    composable(Navigation.Favorites.route) {
                        FavoritesScreen(
                            onHome = {
                                navController.navigate(Navigation.Home.route)
                            },
                            onDiscover = {
                                navController.navigate(Navigation.Discover.route)
                            },
                            selectedScreen = selectedScreen,
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}