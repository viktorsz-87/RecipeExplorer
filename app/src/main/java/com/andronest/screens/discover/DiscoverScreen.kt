package com.andronest.screens.discover

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.andronest.composables.BottomAppBar
import com.andronest.model.Meal
import com.andronest.screens.favorites.FavoritesTopAppBar
import com.andronest.viewmodel.DiscoverViewModel
import com.andronest.viewmodel.FavoritesViewModel

@Composable
fun DiscoverScreen(
    mealId: String? = null,
    navController: NavController,
    onHome: () -> Unit,
    onFavorites: () -> Unit,
    viewModelDiscover: DiscoverViewModel = hiltViewModel(),
    viewModelFavorites: FavoritesViewModel = hiltViewModel(),
    selectedScreen: String?,
    modifier: Modifier = Modifier
) {

    val discoverMealState = viewModelDiscover.discoverResponse.collectAsState()
    val discoverMeal: List<Meal> = discoverMealState.value

    val discoverFetchMealByIdState = viewModelDiscover.fetchMealByIdResponse.collectAsState()
    val discoverFetchMealById: List<Meal> = discoverFetchMealByIdState.value

    mealId?.let {
        viewModelDiscover.fetchMealById(it)
    }

    Scaffold(
        topBar = {
            FavoritesTopAppBar(navController)
        },
        bottomBar = {
            BottomAppBar(
                navController,
                onHome = onHome,
                onFavorites = onFavorites,
                selectedScreen = selectedScreen
            )
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {

            mealId?.let {
                items(discoverFetchMealById) { item ->
                    DiscoverScreenCard(item = item, onAddToFavorites = {
                        viewModelFavorites.insertIntoDatabase(item)
                    })
                }
            } ?: items(discoverMeal) { item ->
                DiscoverScreenCard(item = item, onAddToFavorites = {
                    viewModelFavorites.insertIntoDatabase(item)
                })
            }
        }
    }
}