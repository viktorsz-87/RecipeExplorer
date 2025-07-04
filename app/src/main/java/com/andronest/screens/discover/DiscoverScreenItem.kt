package com.andronest.screens.discover

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.andronest.model.MealResponse.Meal


@Composable
fun DiscoverScreenItem(
    item: Meal,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(6.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {

        AsyncImage(
            model = item.strMealThumb,
            contentDescription = item.strMeal,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(150.dp)
                .clip(RoundedCornerShape(8.dp))
        )
        Spacer(modifier = modifier.width(16.dp))

        Column() {
            item.strMeal?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            item.strCategory?.let { category ->
                Text(text = "Category: $category", style = MaterialTheme.typography.bodyMedium)
            }
            Spacer(modifier = Modifier.height(12.dp))

            // Ingredients here
            Text(
                text = "Ingredients",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            item.apply {

                val ingredients = listOf(
                    strIngredient1, strIngredient2, strIngredient3, strIngredient4,
                    strIngredient5, strIngredient6, strIngredient7, strIngredient8,
                    strIngredient9, strIngredient10, strIngredient11, strIngredient12,
                    strIngredient13, strIngredient14, strIngredient15, strIngredient16,
                    strIngredient17, strIngredient18, strIngredient19, strIngredient20
                )
                    .filter { !it.isNullOrBlank() }
                    .joinToString(", ")

                Text(
                    color = Color.Black,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodySmall,
                    text = ingredients
                )
            }
        }
    }
}