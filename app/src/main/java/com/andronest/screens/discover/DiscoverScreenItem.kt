package com.andronest.screens.discover

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.andronest.model.Meal
import com.andronest.model.MealWithInstructions
import com.andronest.screens.utils.getIngredientsWithMeasure
import com.andronest.screens.utils.getInstructions

@Composable
fun DiscoverScreenItem(
    item: Meal,
    modifier: Modifier = Modifier
) {
    Spacer(modifier = modifier.height(20.dp))
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(6.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Top
    ) {
        AsyncImage(
            model = item.mealThumb,
            contentDescription = item.meal,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
        )
    }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(6.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.Top
    ) {


        Spacer(modifier = modifier.width(16.dp))

        Column(
            modifier=modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top) {
            item.meal?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            item.category?.let { category ->
                Text(text = "Category: $category", style = MaterialTheme.typography.bodyMedium)
            }
            Spacer(modifier = Modifier.height(12.dp))


        }
    }
    // Using reflection api to get these. Check Utils package
    val instructions = getInstructions(item)
    val ingredientsWithMeasure = getIngredientsWithMeasure(item)

    val mealWithInstr = MealWithInstructions(
        instructions = instructions,
        ingredientsWithMeasure = ingredientsWithMeasure
    )

    // Instructions

    Spacer(modifier = modifier
        .fillMaxWidth()
        .height(20.dp))

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
    ) {

        // Ingredients here
        Text(
            text = "Instructions",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
    }
    Spacer(modifier = modifier
        .fillMaxWidth()
        .height(20.dp))

    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        // Ingredients here
        Text(
            lineHeight = 20.sp,
            textAlign = TextAlign.Justify,
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            text = instructions ?: "No instructions found",
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Bold
        )
    }

    Spacer(modifier = modifier
        .fillMaxWidth()
        .height(20.dp))
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
    ) {

        // Ingredients here
        Text(
            text = "Ingredients",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
    }
    Spacer(modifier = modifier
        .fillMaxWidth()
        .height(20.dp))
    // Ingredients
    IngredientsList(mealWithInstr)


}
