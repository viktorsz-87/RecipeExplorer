package com.andronest.screens.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.andronest.screens.utils.ConnectivityStatus

@Composable
fun NetworkStatusBanner(
    status: ConnectivityStatus,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        when (status) {
            ConnectivityStatus.Loading -> Text(
                text = "Loading...",
                style = MaterialTheme.typography.titleSmall,
                color = Color.Red
            )
            is ConnectivityStatus.Error -> Text(
                text = status.error,
                style = MaterialTheme.typography.titleSmall,
                color = Color.Red
            )
            else -> {} // Don't show anything for success state
        }
    }


}