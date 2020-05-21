package com.example.helper.util

import android.content.Context
import com.example.helper.R
import com.example.networking.util.LogUtil
import java.io.*

object CertificateUtil {

    fun certificate(context: Context): InputStream {

        try {
            return context.resources.openRawResource(R.raw.cert)
        } catch (exception: Exception) {
            LogUtil.e("z- exception", exception.toString())
            error("Cannot read certificate")
        }
    }
}