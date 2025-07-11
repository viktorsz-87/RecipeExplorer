package com.andronest.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.andronest.R
import com.andronest.composables.BottomAppBar
import com.andronest.model.Meal
import com.andronest.viewmodel.HomeViewModel
import kotlinx.coroutines.flow.collectLatest


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

    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    // This block runs:
    // 1. ONCE when composable first enters composition
    // 2. NEVER again during recompositions
    // 3. UNLESS the composable leaves and re-enters composition
    LaunchedEffect(Unit) {

        viewModel.errorEvents.collectLatest { errorMessage->
            snackbarHostState.showSnackbar(errorMessage)
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
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

        Column(Modifier
            .padding(paddingValues)
            .fillMaxSize()) {

            // Search Input
            OutlinedTextField(
                textStyle = MaterialTheme.typography.bodyMedium,
                maxLines = 1,
                label = {
                    Text(
                        text = stringResource(R.string.search_by_ingredient),
                        style = MaterialTheme.typography.bodyMedium
                    )
                },
                value = uiState.value.searchQuery,
                onValueChange = {
                    //viewModel.searchText = it
                    viewModel::searchMealByIngredient
                },
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                placeholder = { Text(stringResource(R.string.search_placeholder)) }
            )

            Spacer(Modifier.height(16.dp))

            when{
                uiState.value.isLoading -> LoadingState()
                uiState.value.error != null -> ErrorState(uiState.value.error?: "Error")
                uiState.value.meals.isEmpty() -> EmptyState()
                else -> MealListContent(uiState.value)
            }
        }
    }
}

@Composable
fun MealListContent(
    uiState: HomeViewModel.HomeUiState,
    modifier: Modifier = Modifier) {

    // Remember recalculates only when object ref changes
    // derivedState of: recalculates when the contents of the state changes

    val displayList:List<Meal> = remember(uiState){
        when {
            !uiState.searchResults.isNullOrEmpty() -> uiState.searchResults
            !uiState.meals.isNullOrEmpty() -> uiState.meals
            else -> emptyList()
        }
    }

    LazyColumn {

        if(uiState.isSearching){
            item {
                Column(modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally){

                    CircularProgressIndicator()
                    Spacer(Modifier.height(8.dp))
                    Text(stringResource(R.string.searching))
                }
            }
        }

        uiState.error?.let { error->
            item{
                Text(
                    text =  stringResource(R.string.search_error, error),
                    color = MaterialTheme.colorScheme.error,
                    modifier= Modifier.padding(16.dp))
            }
        }

        if(displayList.isEmpty()){
            item{
                Text(
                    text = stringResource(R.string.no_results_found),
                    color = MaterialTheme.colorScheme.error,
                    modifier= Modifier.padding(16.dp))
            }
        } else {
            items(displayList){meal->
                HomeScreenItemCard(
                    navController = rememberNavController(),
                    item=meal,
                    modifier= Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
        }

    }
}



