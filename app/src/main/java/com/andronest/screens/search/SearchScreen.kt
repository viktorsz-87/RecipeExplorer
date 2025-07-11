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
import com.andronest.viewmodel.NetworkViewModel
import com.andronest.viewmodel.SearchViewModel

@Composable
fun SearchScreen(
    onHome: () -> Unit,
    onFavorites: () -> Unit,
    onDiscover: () -> Unit,
    navController: NavController,
    selectedScreen: String?,
    searchViewModel: SearchViewModel = hiltViewModel(),
    networkViewModel: NetworkViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {

    /* collectAsStateWithLifecycle: Lifecycle aware,
       runs only in fireground and not when screen is not visible.*/

    val meals by searchViewModel.meals.collectAsStateWithLifecycle()
    val mealsByCategories by searchViewModel.mealsByCategory.collectAsStateWithLifecycle()
    val selectedMode = searchViewModel.selectedMode

    val networkState by networkViewModel.status.collectAsStateWithLifecycle()

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
                searchText = searchViewModel.searchText,
                onSearchChange = searchViewModel::searchMealByName,
                categories = searchViewModel.categories,
                selectedCategory = searchViewModel.selectedCategory,
                onCategorySelected = searchViewModel::updateCategory,
                onChipClicked = searchViewModel::searchMealsByCategory,
                onSelectedMode = searchViewModel.selectedMode,
                onModeSelected = searchViewModel::updateMode,
                modes = searchViewModel.modes
            )

            Spacer(modifier = Modifier.height(10.dp))

            NetworkStatusBanner(networkViewModel = networkViewModel)

            MealsListContent(
                isFilteringEnabled = searchViewModel.isFilterEnabled,
                selectedMode = selectedMode,
                meals = meals,
                mealsByCategories = mealsByCategories,
                navController = navController,
                modifier = modifier,
                viewModel = searchViewModel
            )
        }
    }
}