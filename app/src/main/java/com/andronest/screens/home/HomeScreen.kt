package com.andronest.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
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
    onDiscover: ()->Unit,
    viewModel: HomeViewModel = hiltViewModel(),
    selectedIndex: Int,
    modifier: Modifier = Modifier
) {

    val mealsDataState = viewModel.mealsResponse.collectAsState(emptyList())
    val mealsData: List<Meal> = mealsDataState.value

    Scaffold(
        topBar = { HomeTopAppBar() },
        bottomBar = {
            BottomAppBar(
                navController,
                onDiscover = onDiscover,
                selectedIndex = selectedIndex,

            )
        }) { paddingValues ->

        Column(modifier = Modifier.padding(paddingValues)) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(4.dp)
            ) {

                items(items = mealsData) { meal ->
                    HomeScreenItemCard(meal)
                }

            }
        }
    }
}