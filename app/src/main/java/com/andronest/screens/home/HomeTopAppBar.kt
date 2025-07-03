package com.andronest.screens.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import com.andronest.ui.theme.Primary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppBar(modifier: Modifier = Modifier) {

    TopAppBar(
        actions = {

            IconButton(onClick = {}) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu options")
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Primary.copy(alpha = 0.6f),
            titleContentColor = MaterialTheme.colorScheme.primary
        ),
        title = {
            Text(
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                ),
                text = "Home", maxLines = 1, overflow = TextOverflow.Ellipsis
            )
        })
}