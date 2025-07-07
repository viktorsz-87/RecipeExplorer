package com.andronest.screens.favorites

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.andronest.composables.BottomAppBar
import com.andronest.composables.ConfirmSnackbar
import com.andronest.composables.CustomTopAppBar
import com.andronest.navigation.Navigation
import com.andronest.viewmodel.FavoritesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(
    onDiscover: () -> Unit,
    onHome: () -> Unit,
    onSearch: () -> Unit,
    selectedScreen: String?,
    navController: NavController,
    viewModel: FavoritesViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {

    val allMealsState = viewModel.allMeals.collectAsState()
    val allMeals = allMealsState.value
    val snackBarState by viewModel.snackBarState.collectAsState()

    Scaffold(
        snackbarHost = {

            when (snackBarState) {
                is FavoritesViewModel.SnackBarState.Visible -> {

                    ConfirmSnackbar(
                        onDismiss = { viewModel.hideSnackBar() },
                        message = "Removed from favorites!")
                }
                else -> {}
            }
        },
        topBar = {
            CustomTopAppBar(navController)
        },
        bottomBar = {
            BottomAppBar(
                navController = navController,
                onDiscover = onDiscover,
                onHome = onHome,
                onSearch = onSearch,
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

                // Create meal card
                FavoritesScreenCard(
                    meal, navController, viewModel,
                    onItemClick = { navController.navigate(Navigation.Discover.createRoute(meal.idMeal)) },
                    onItemDelete = {
                        viewModel.deleteFromDatabase(meal);viewModel.fetchAllMealsFromDatabase(); viewModel.showSnackBar(
                        "Item removed from favorites!"
                    )
                    }
                )
            }
        }
    }
}