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
import com.andronest.model.MealResponse.Meal
import com.andronest.screens.recipe.RecipeTopAppBar
import com.andronest.viewmodel.DiscoverViewModel

@Composable
fun DiscoverScreen(
    navController: NavController,
    onHome: ()->Unit,
    viewModel: DiscoverViewModel = hiltViewModel(),
    selectedScreen: String?,
    modifier: Modifier = Modifier) {

    val discoverMealState = viewModel.discoverResponse.collectAsState()
    val discoverMeal:List<Meal> = discoverMealState.value

    Scaffold(
        topBar = {
            RecipeTopAppBar(navController)
        },
        bottomBar ={
            BottomAppBar(
                navController,
                onHome = onHome,
                selectedScreen = selectedScreen)
        }
    ) { paddingValues ->

        LazyColumn(modifier = modifier
            .padding(paddingValues)
            .fillMaxSize()){

            items(discoverMeal){item->
                DiscoverScreenCard(item)
            }


        }
    }
}