package com.andronest.model

open class ConnectivityStatus{
    data class Success(val msg: String) : ConnectivityStatus()
    data class Error(val error: String) : ConnectivityStatus()
    object Loading : ConnectivityStatus()

}