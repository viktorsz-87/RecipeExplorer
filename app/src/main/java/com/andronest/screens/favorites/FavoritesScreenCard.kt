package com.andronest.screens.favorites

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.andronest.model.Meal
import com.andronest.viewmodel.FavoritesViewModel

@Composable
fun FavoritesScreenCard(
    meal: Meal,
    navController: NavController,
    viewModel: FavoritesViewModel,
    onItemClick: () -> Unit,
    onItemDelete: () -> Unit,
    modifier: Modifier = Modifier
) {

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        border = BorderStroke(2.dp, color = Color.Black)
    ) {

        FavoritesScreenItem(
            meal, navController,
            onItemClick = onItemClick,
            onItemDelete = onItemDelete)
    }
}