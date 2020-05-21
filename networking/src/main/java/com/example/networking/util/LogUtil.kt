package com.example.networking.util

import android.util.Log
import com.example.networking.BuildConfig

object LogUtil {

    /* info */
    fun i(key: String, value: String) {
        if (BuildConfig.DEBUG) {
            Log.i(checkSize(key), value)
        }
    }

    /* debug */
    fun d(key: String, value: String) {
        if (BuildConfig.DEBUG) {
            Log.d(checkSize(key), value)
        }
    }

    /* error */
    fun e(key: String, value: String) {
        if (BuildConfig.DEBUG) {
            Log.e(checkSize(key), value)
        }
    }

    /* verbose */
    fun v(key: String, value: String) {
        if (BuildConfig.DEBUG) {
            Log.v(checkSize(key), value)
        }
    }

    /* warning */
    fun w(key: String, value: String) {
        if (BuildConfig.DEBUG) {
            Log.w(checkSize(key), value)
        }
    }

    /* Validate the maximum size */
    private fun checkSize(value: String): String {
        require(value.length <= 23) {
            "The logging tag can be at mos 23 characters, was ${value.length} ($value)"
        }
        return if (value.length <= 23) value else value.substring(0, 23)
    }
}