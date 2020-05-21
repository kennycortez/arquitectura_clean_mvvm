package com.example.networking.util

import java.io.*
import java.nio.charset.StandardCharsets

object InputStreamUtil {

    fun inputStreamToString(inputStream: InputStream): String {
        val bis = BufferedInputStream(inputStream)
        val buf = ByteArrayOutputStream()
        var result = bis.read()
        while (result != -1) {
            buf.write(result.toByte().toInt())
            result = bis.read()
        }
        return buf.toString("UTF-8")
    }

    fun stringToInputStream(value: String): InputStream {
        return ByteArrayInputStream(value.toByteArray(StandardCharsets.UTF_8))
    }
}