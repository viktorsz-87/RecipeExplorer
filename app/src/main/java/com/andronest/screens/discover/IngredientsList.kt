package com.andronest.screens.discover

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.andronest.model.MealWithInstructions

@Composable
fun IngredientsList(
    mealWithInstr: MealWithInstructions,
    modifier: Modifier = Modifier
) {
    mealWithInstr.ingredientsWithMeasure.forEach {
        Row(modifier=modifier
            .fillMaxWidth()) {

            Column(

                modifier=Modifier.padding(5.dp).weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally){

                Text(
                    text = it.first,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold)
            }

            Spacer(modifier.width(5.dp))

            Column(modifier=Modifier.padding(5.dp).weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally){

                Text(
                    text = it.second,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold)
            }

        }
    }
}



