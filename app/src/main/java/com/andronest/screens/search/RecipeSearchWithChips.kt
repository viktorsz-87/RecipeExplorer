package com.andronest.screens.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.FilterChip
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@Composable
fun RecipeSearchWithChips(
    searchText: String,
    onSearchChange: (String) -> Unit,
    categories: List<String>,
    modes: List<String>,
    selectedCategory: String?,
    onCategorySelected: (String?) -> Unit,
    onChipClicked: (String) -> Unit,
    onSelectedMode: String,
    onModeSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    Column(modifier = modifier.padding(16.dp)) {

        OutlinedTextField(
            label = { Text("Meal name") },
            value = searchText,
            onValueChange = onSearchChange,
            placeholder = { Text("Beef..") },
            modifier = modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            colors = TextFieldDefaults.colors()
        )

        Spacer(Modifier.height(16.dp))

        // Chips here
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {

            items(categories.size) { index ->
                FilterChip(
                    selected = selectedCategory == categories[index],
                    onClick = {
                        onCategorySelected(categories[index])
                        onChipClicked(categories[index])
                    },
                    label = { Text(categories[index]) }
                )
            }
        }

        Spacer(Modifier.height(6.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ){

            items(modes.size){ index->
                FilterChip(
                    selected = modes[index] == onSelectedMode,
                    onClick = {
                        onModeSelected(modes[index])
                        onSearchChange(searchText)
                    },
                    label = { Text(modes[index]) }
                )
            }

        }
    }
}