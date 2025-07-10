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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.andronest.R
import com.andronest.composables.BottomAppBar
import com.andronest.composables.CustomTopAppBar
import com.andronest.model.Meal
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

    /* collectAsStateWithLifecycle: Lifecycle aware,
       runs only in fireground and not when screen is not visible.*/

    val meals by viewModel.meals.collectAsStateWithLifecycle()
    val mealsByCategories by viewModel.mealsByCategory.collectAsStateWithLifecycle()
    val networkStatus by viewModel.networkStatus.collectAsStateWithLifecycle()

    val selectedMode = viewModel.selectedMode

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

            NetworkStatusBanner(status = networkStatus)

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

@Composable
fun NetworkStatusBanner(
    status: ConnectivityStatus,
    modifier: Modifier = Modifier
) {

    when (status) {
        ConnectivityStatus.Loading,
        is ConnectivityStatus.Error -> {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = when (status) {
                        ConnectivityStatus.Loading -> stringResource(R.string.loading)
                        else -> stringResource(R.string.no_connection)
                    },
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }

        else -> Text("")
    }
}
