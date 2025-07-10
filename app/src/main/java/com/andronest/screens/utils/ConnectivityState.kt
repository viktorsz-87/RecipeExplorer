package com.andronest.screens.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext


@Composable
fun rememberConnectivityState(): MutableState<ConnectivityStatus> {

    val status = remember { mutableStateOf<ConnectivityStatus>(ConnectivityStatus.Loading) }
    val context = LocalContext.current

    val connManger = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val callback = object : ConnectivityManager.NetworkCallback(){
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            status.value = ConnectivityStatus.Success("Connected!")
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            status.value = ConnectivityStatus.Error("Connection Lost!")
        }

        override fun onUnavailable() {
            super.onUnavailable()
            status.value = ConnectivityStatus.Error("No Connection!")
        }

        override fun onCapabilitiesChanged(
            network: Network,
            networkCapabilities: NetworkCapabilities
        ) {
            val capabilities = connManger.getNetworkCapabilities(connManger.activeNetwork)

                status.value = when{

                    capabilities == null -> ConnectivityStatus.Error("No active network")
                    capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) -> ConnectivityStatus.Success("Connected")
                    else -> ConnectivityStatus.Error("No internet access")
                }

            }
    }
    connManger.registerDefaultNetworkCallback(callback)

    return status
}

sealed class ConnectivityStatus{
    data class Success(val msg: String) : ConnectivityStatus()
    data class Error(val error: String) : ConnectivityStatus()
    object Loading : ConnectivityStatus()

}