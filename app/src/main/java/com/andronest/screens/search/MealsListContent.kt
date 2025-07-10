package com.andronest.screens.search

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.andronest.model.Meal
import com.andronest.viewmodel.SearchViewModel

@Composable
fun MealsListContent(
    isFilteringEnabled: Boolean,
    selectedMode: String,
    meals: List<Meal>?,
    mealsByCategories: List<Meal>?,
    navController: NavController,
    viewModel: SearchViewModel,
    modifier: Modifier = Modifier
) {

    /*  Only update based on dependencies. Otherwise it will update based on any
        unrelated state change(theme change or rotation screen) causing unnecessary
        calculations and also affecting the lazy column to rebuild itself since the
        calculations was previously done inside the LazyColumn

        We declare explicitly  which states affect the list calculation
        */

    val displayList = remember(meals, mealsByCategories, selectedMode, isFilteringEnabled) {

        when {
            viewModel.isOfflineWithMeals -> meals
            viewModel.isOnlineFilteredWithResults -> mealsByCategories
            !meals.isNullOrEmpty() -> meals
            else -> emptyList()
        }
    }

    LazyColumn {
        displayList?.let {
            items(displayList) { meal ->
                SearchScreenItemCard(navController, meal)
            }
        }
    }
}