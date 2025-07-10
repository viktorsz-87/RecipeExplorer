package com.andronest.screens.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.andronest.composables.BottomAppBar
import com.andronest.composables.CustomTopAppBar
import com.andronest.screens.utils.rememberConnectivityState
import com.andronest.viewmodel.SearchViewModel

@Composable
fun SearchScreen(
    onHome: () -> Unit,
    onFavorites: () -> Unit,
    onDiscover: () -> Unit,
    navController: NavController,
    selectedScreen: String?,
    viewModel: SearchViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {

    /* collectAsStateWithLifecycle: Lifecycle aware,
       runs only in fireground and not when screen is not visible.*/

    val meals by viewModel.meals.collectAsStateWithLifecycle()
    val mealsByCategories by viewModel.mealsByCategory.collectAsStateWithLifecycle()
    val selectedMode = viewModel.selectedMode

    val networkState = rememberConnectivityState().value

    Scaffold(
        topBar = {
            CustomTopAppBar(navController)
        },
        bottomBar = {
            BottomAppBar(
                navController,
                onHome = onHome,
                onFavorites = onFavorites,
                onDiscover = onDiscover,
                selectedScreen = selectedScreen
            )
        }
    ) { paddingValues ->

        Column(
            modifier = modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {

            RecipeSearchWithChips(
                searchText = viewModel.searchText,
                onSearchChange = viewModel::searchMealByName,
                categories = viewModel.categories,
                selectedCategory = viewModel.selectedCategory,
                onCategorySelected = viewModel::updateCategory,
                onChipClicked = viewModel::searchMealsByCategory,
                onSelectedMode = viewModel.selectedMode,
                onModeSelected = viewModel::updateMode,
                modes = viewModel.modes
            )

            Spacer(modifier = Modifier.height(10.dp))

            NetworkStatusBanner(status = networkState)

            MealsListContent(
                isFilteringEnabled = viewModel.isFilterEnabled,
                selectedMode = selectedMode,
                meals = meals,
                mealsByCategories = mealsByCategories,
                navController = navController,
                modifier = modifier,
                viewModel = viewModel
            )
        }
    }
}