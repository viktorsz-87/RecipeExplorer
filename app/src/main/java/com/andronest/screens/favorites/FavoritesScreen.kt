package com.andronest.screens.favorites

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.andronest.composables.BottomAppBar
import com.andronest.viewmodel.FavoritesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(
    onDiscover: () -> Unit,
    onHome: () -> Unit,
    selectedScreen: String?,
    navController: NavController,
    viewModel: FavoritesViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {

    val allMealsState = viewModel.allMeals.collectAsState()
    val allMeals = allMealsState.value

    Scaffold(
        topBar = {
            FavoritesTopAppBar(navController)
        },
        bottomBar = {
            BottomAppBar(
                navController = navController,
                onDiscover = onDiscover,
                onHome = onHome,
                selectedScreen = selectedScreen
            )
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {

            items(items = allMeals) { meal ->

                // NOTE: For now empty, no data will be shown on screen!

                // Create meal card
                FavoritesScreenCard(meal, navController)
            }
        }
    }
}