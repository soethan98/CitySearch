package com.soethan.citysearch.utils

import android.content.Context
import android.net.ConnectivityManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ConnectivityChecker  @Inject constructor(@ApplicationContext private val context: Context) {

    fun isNetworkAvailable(): Boolean {
        val service = Context.CONNECTIVITY_SERVICE
        val manager = context.getSystemService(service) as ConnectivityManager?
        val network = manager?.activeNetworkInfo
        return network != null
    }

}