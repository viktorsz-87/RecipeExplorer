package com.andronest.screens.favorites

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.andronest.composables.BottomAppBar
import com.andronest.navigation.Navigation
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

    var showSnackBar by remember { mutableStateOf(false) }
    var snackbarMessage by remember { mutableStateOf("") }



    Scaffold(
        snackbarHost = {
            if(showSnackBar){
                Snackbar(
                    modifier= Modifier.padding(16.dp),
                    shape = RoundedCornerShape(8.dp),
                    containerColor = Color(0xFF4CAF50),
                    contentColor = Color.White) {
                    Text(text = snackbarMessage)
                }
            }
        },
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

                // Create meal card
                FavoritesScreenCard(meal, navController, viewModel,
                    onItemClick = { navController.navigate(Navigation.Discover.createRoute(meal.idMeal)) },
                    onItemDelete = {viewModel.deleteFromDatabase(meal);viewModel.fetchAllMealsFromDatabase(); }
                )
            }
        }
    }
}