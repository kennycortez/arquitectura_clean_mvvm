package com.example.storage.provider

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import java.util.concurrent.ConcurrentLinkedQueue

object CoreNetworkingProvider {
    internal val applicationListeners = ConcurrentLinkedQueue<(Application) -> Unit>()

    @JvmStatic
    fun listen(listener: (Application) -> Unit) {
        val app = localApplication
        if (app != null) {
            listener(app)
        } else {
            applicationListeners.add(listener)
        }
    }

    @JvmStatic
    val application: Application?
        get() {
            return localApplication
        }
}

@SuppressLint("StaticFieldLeak")
private var localApplication: Application? = null
    private set(value) {
        field = value
        if (value != null) {
            CoreNetworkingProvider.applicationListeners.forEach {
                it.invoke(value)
            }
        }
    }

val application: Application?
    get() = localApplication ?: initAndGetAppCtxWithReflection()

/**
 * This methods is only run if [appCtx] is accessed while [AppCtxInitProvider] hasn't been
 * initialized. This may happen in case you're accessing it outside the default process, or in case
 * you are accessing it in a [ContentProvider] with a higher priority than [AppCtxInitProvider]
 * (900 at the time of writing this doc).
 *
 * //from https://github.com/LouisCAD/Splitties/tree/master/appctx
 */
@SuppressLint("PrivateApi", "DiscouragedPrivateApi")
private fun initAndGetAppCtxWithReflection(): Application? {
    // Fallback, should only run once per non default process.
    val activityThread = Class.forName("android.app.ActivityThread")
    val ctx = activityThread.getDeclaredMethod("currentApplication").invoke(null) as? Context
    if (ctx is Application) {
        localApplication = ctx
        return ctx
    }
    return null
}

class AppContextProvider : EmptyProvider() {
    override fun onCreate(): Boolean {
        val ctx = context
        if (ctx is Application) {
            localApplication = ctx
        }
        return true
    }
}