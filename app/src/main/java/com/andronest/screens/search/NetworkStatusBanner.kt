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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.andronest.R
import com.andronest.screens.utils.ConnectivityStatus

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