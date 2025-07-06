package com.andronest.screens.favorites

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(
    navController: NavController,
    modifier: Modifier = Modifier) {

    Scaffold(
        topBar = {
            FavoritesTopAppBar(navController)
        }
    ) { paddingValues ->

        Column(modifier = modifier
            .padding(paddingValues)
            .fillMaxSize()){

            Text(text = "Favorites")
        }
    }
}