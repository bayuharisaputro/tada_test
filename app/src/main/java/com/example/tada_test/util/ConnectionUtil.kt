package com.example.tada_test.util

import android.content.Context
import android.net.ConnectivityManager
import com.example.tada_test.dependencyInjection.component.BaseApp
import javax.inject.Inject

class ConnectionUtil @Inject constructor(private val context: BaseApp) {
    fun isInternetConnected(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting
    }
}