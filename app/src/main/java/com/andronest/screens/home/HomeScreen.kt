package com.andronest.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.andronest.composables.BottomAppBar
import com.andronest.model.Meal
import com.andronest.viewmodel.HomeViewModel


@Composable
fun HomeScreen(
    navController: NavController,
    onDiscover: () -> Unit,
    onFavorites: () -> Unit,
    onSearch: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
    selectedScreen: String?,
    modifier: Modifier = Modifier
) {
    val mealsDataState = viewModel.mealsResponse.collectAsState()
    val mealsData: List<Meal> = mealsDataState.value

    Scaffold(
        topBar = { HomeTopAppBar() },
        bottomBar = {
            BottomAppBar(
                navController,
                onDiscover = onDiscover,
                onFavorites = onFavorites,
                onSearch = onSearch,
                selectedScreen = selectedScreen
            )
        }) { paddingValues ->

        Column(Modifier.padding(paddingValues)) {
            // Search Input
            OutlinedTextField(
                textStyle = MaterialTheme.typography.bodyMedium,
                maxLines = 1,
                label = { Text(text = "Search by ingredient", style = MaterialTheme.typography.bodyMedium) },
                value = viewModel.searchText,
                onValueChange = {
                    viewModel.searchText = it
                    viewModel.searchMealByIngredient(it)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                placeholder = { Text("Garlic..") }
            )

            Spacer(Modifier.height(16.dp))

            // Results List
            LazyColumn {

                viewModel.results.takeIf { it.isNotEmpty() }?.let { result ->

                    items(items = result) { item ->
                        HomeScreenItemCard(
                            navController = navController,
                            item = item
                        )
                    }
                } ?: run {
                    items(mealsData) {
                        HomeScreenItemCard(
                            navController = navController,
                            item = it
                        )
                    }
                }
            }
        }
    }
}
