package com.example.cinephile.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.M)
fun isOnline(context: Context?): Boolean {
    val connectivityManager =
        context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val capabilities =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            } else {
                return false
            }

    if (capabilities != null) {
          if(capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)){
              //  Log.i("Internet", "Has network Connectivity")
                return true
            }
        }

    return false
}
