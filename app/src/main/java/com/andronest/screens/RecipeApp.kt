package com.andronest.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.andronest.composables.BottomAppBar
import com.andronest.composables.MyTopAppBar


@Composable
fun RecipeApp(modifier: Modifier = Modifier) {

    Scaffold(
        topBar = { MyTopAppBar() },
        bottomBar = { BottomAppBar() },
        modifier = Modifier.fillMaxSize()) {paddingValues->

        HomeScreen(modifier = modifier.padding(paddingValues))
    }
}