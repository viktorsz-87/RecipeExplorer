package com.andronest.screens.home

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.andronest.model.MealResponse.Meal
import com.andronest.ui.theme.ShowDetails

@Composable
fun HomeScreenItem(
    item: Meal,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier) {

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

        Column(){
            item.strMeal?.let {
                Text(text = it,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold)
            }

            Spacer(modifier= Modifier.height(4.dp))

            item.strCategory?.let {category->
                Text(text = "Category: $category", style= MaterialTheme.typography.bodyMedium)
            }
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                modifier = Modifier.clickable(onClick = { onClick() }),
                color = ShowDetails,
                text = "Show details",
                style = MaterialTheme.typography.titleMedium)
        }
    }
}