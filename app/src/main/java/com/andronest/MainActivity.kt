package com.andronest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.andronest.navigation.Navigation
import com.andronest.screens.discover.DiscoverScreen
import com.andronest.screens.home.HomeScreen
import com.andronest.screens.recipe.RecipeScreen
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
                var selectedIndex by rememberSaveable { mutableIntStateOf(0) }

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
                            selectedIndex = selectedIndex
                        )
                    }
                    composable(Navigation.Discover.route) {
                        DiscoverScreen(
                            navController,
                            onHome = {
                                navController.navigate(Navigation.Home.route)
                            },
                            selectedIndex = selectedIndex,
                        )
                    }

                    composable(Navigation.Recipe.route) {

                        val id: Int? = it.arguments?.getInt("idMeal")
                        if (id != null) {
                            RecipeScreen(
                                navController = navController,
                                idMeal = id
                            )
                        }
                    }
                }
            }
        }
    }
}