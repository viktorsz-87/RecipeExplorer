package com.andronest.screens.discover

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun IngredientItem(
    ingredient: String,
    measure: String,
    modifier: Modifier = Modifier) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(vertical = 8.dp)
    ) {

        Text(text = ingredient, style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = measure, style = MaterialTheme.typography.bodyMedium)
    }
}