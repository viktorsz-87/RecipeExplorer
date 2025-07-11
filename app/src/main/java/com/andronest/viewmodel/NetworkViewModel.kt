package com.andronest.viewmodel

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andronest.model.ConnectivityStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NetworkViewModel @Inject constructor (
    private val connectivityManager: ConnectivityManager
) : ViewModel(){

    private val _status = MutableStateFlow<ConnectivityStatus>(ConnectivityStatus.Loading)
    val status: StateFlow<ConnectivityStatus> = _status.asStateFlow()


    private val callback = object : ConnectivityManager.NetworkCallback(){
        override fun onAvailable(network: Network) {

        }

        override fun onLost(network: Network) {

        }

        override fun onCapabilitiesChanged(
            network: Network,
            networkCapabilities: NetworkCapabilities
        ) {
            _status.value = if(networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)){
                ConnectivityStatus.Success("Connected ${getNetworkTransportType(networkCapabilities)}")
            } else {
                ConnectivityStatus.Error("No internet access")
            }
        }
    }

    fun getNetworkTransportType(capabilities: NetworkCapabilities): String{

        return when{
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> "Wifi"
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> "Cellular"
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> "Ethernet"

            else -> "Unknown"
        }
    }

    private fun startMonitoring(){

        checkCurrentStatus()

        viewModelScope.launch {
            try {
                connectivityManager.registerDefaultNetworkCallback(callback)
            } catch (e: Exception){
                _status.value = ConnectivityStatus.Error("Monitoring failed")
            }
        }
    }

    private fun checkCurrentStatus(){
        val activeNetwork = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork)

        _status.value = when{

            capabilities == null -> ConnectivityStatus.Error("No Network")
            capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) -> ConnectivityStatus.Success("Connected")
            else -> ConnectivityStatus.Error("No internet")
        }
    }

    override fun onCleared() {
        super.onCleared()
        connectivityManager.unregisterNetworkCallback(callback)
    }

    init {
       startMonitoring()
    }

}