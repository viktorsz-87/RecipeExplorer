package com.andronest.screens.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.andronest.composables.BottomAppBar
import com.andronest.composables.CustomTopAppBar

@Composable
fun SearchScreen(
    onHome: () -> Unit,
    onFavorites: () -> Unit,
    onDiscover: () -> Unit,
    navController: NavController,
    selectedScreen: String?,
    modifier: Modifier = Modifier
) {

    // Search screen needs its own viewmodel
    var text by rememberSaveable { mutableStateOf("") }

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
            Column(
                modifier = modifier
                    .padding(16.dp)
                    .fillMaxSize()
            ) {
                OutlinedTextField(
                    value = text,
                    onValueChange = { newValue -> text = newValue },
                    label = {
                        Text("Search..")
                    },
                    modifier = modifier.fillMaxWidth()
                )
            }
        }
    }
}
