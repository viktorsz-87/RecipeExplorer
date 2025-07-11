package com.andronest.screens.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.andronest.model.ConnectivityStatus
import com.andronest.viewmodel.NetworkViewModel

@Composable
fun NetworkStatusBanner(
    networkViewModel: NetworkViewModel,
    modifier: Modifier = Modifier
) {

    val networkStatus by networkViewModel.status.collectAsStateWithLifecycle()

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        when (networkStatus) {
            is ConnectivityStatus.Loading -> Text(
                text = "Loading..",
                style = MaterialTheme.typography.titleSmall,
                color = Color.Red
            )
            is ConnectivityStatus.Error -> Text(
                text = (networkStatus as ConnectivityStatus.Error).error,
                style = MaterialTheme.typography.titleSmall,
                color = Color.Red
            )
            else -> {} // Don't show anything for success state
        }
    }
}