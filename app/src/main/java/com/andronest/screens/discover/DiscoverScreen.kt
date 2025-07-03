package com.andronest.screens.discover

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.andronest.composables.BottomAppBar
import com.andronest.screens.recipe.RecipeTopAppBar
import com.andronest.viewmodel.DiscoverViewModel

@Composable
fun DiscoverScreen(
    navController: NavController,
    onHome: ()->Unit,
    viewModel: DiscoverViewModel = hiltViewModel(),
    selectedIndex: Int,
    modifier: Modifier = Modifier) {

    Scaffold(
        topBar = {
            RecipeTopAppBar(navController)
        },
        bottomBar ={
            BottomAppBar(
                navController,
                onHome = onHome,
                selectedIndex = selectedIndex)
        }
    ) { paddingValues ->

        Column(modifier = modifier
            .padding(paddingValues)
            .fillMaxSize()){

            Text(text = "Discover Screen")
        }
    }
}