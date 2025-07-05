package com.andronest.screens.home

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
import com.andronest.model.MealResponse.Meal
import com.andronest.navigation.Navigation


@Composable
fun HomeScreenItemCard(
    navController: NavController,
    item: Meal,
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


        HomeScreenItem(
            item = item,
            modifier = modifier,
            onClick = { navController.navigate(Navigation.Discover.createRoute(item.idMeal)) },
        )

    }
}