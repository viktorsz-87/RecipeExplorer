package com.andronest.screens.discover

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RecipeDetailScreen(modifier: Modifier = Modifier) {

    val scrollState = rememberScrollState()

    Column(modifier = Modifier
        //.verticalScroll(scrollState)
        .padding(8.dp)
    ){

        Text(text = "Ingredients",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = modifier.padding(vertical = 8.dp)
        )

        // ingredient list


        // divider

        // instructions

    }
}