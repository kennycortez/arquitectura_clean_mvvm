package com.example.storage.util

import android.content.Context
import com.example.storage.provider.CoreNetworkingProvider
import com.example.storage.provider.application

object Util {

    fun context(): Context {
        var context: Context? = null
        CoreNetworkingProvider.listen {
            if (application != null) {
                context = application!!.applicationContext
            } else {
                throw IllegalStateException("context == null")
            }
        }
        return context!!
    }
}