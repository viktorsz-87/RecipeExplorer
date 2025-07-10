package com.andronest.screens.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.andronest.composables.BottomAppBar
import com.andronest.composables.CustomTopAppBar
import com.andronest.screens.utils.ConnectivityState
import com.andronest.screens.utils.ConnectivityStatus
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
    val mealsState = viewModel.meals.collectAsState()
    val meals = mealsState.value

    val mealsByCategoriesState = viewModel.mealsByCategory.collectAsState()
    val mealsByCategories = mealsByCategoriesState.value

    val networkStatus = ConnectivityState()

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

            Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth().padding(5.dp)) {
                when(networkStatus.value){
                    is ConnectivityStatus.Loading -> Text(
                        text = "Loading...",
                        style = MaterialTheme.typography.titleSmall,
                        color = Color.Red)

                    is ConnectivityStatus.Error -> Text(
                        text = "No connection",
                        style = MaterialTheme.typography.titleSmall,
                        color = Color.Red)
                    else -> Text("")
                }
            }

            LazyColumn(modifier = modifier.weight(1f)) {

                if (viewModel.selectedMode == "Offline") {
                    items(meals) { meal ->
                        SearchScreenItemCard(
                            item = meal,
                            navController = navController,
                            modifier = modifier
                        )
                    }
                }
                else {
                    if (viewModel.isFilterEnabled() && !mealsByCategories.isNullOrEmpty()) {

                        items(mealsByCategories) { meal ->
                            SearchScreenItemCard(
                                item = meal,
                                navController = navController,
                                modifier = modifier
                            )
                        }
                    }
                    else if (!meals.isNullOrEmpty()) {

                        items(meals) { meal ->
                            SearchScreenItemCard(
                                item = meal,
                                navController = navController,
                                modifier = modifier
                            )
                        }
                    }


                }
            }
        }
    }
}


