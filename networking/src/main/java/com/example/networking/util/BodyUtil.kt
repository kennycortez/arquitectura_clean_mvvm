package com.example.networking.util

import com.example.networking.model.NetworkingConfiguration

object BodyUtil {

    fun getBodyFormUrlEncoded(bcpNetworkingConfiguration: NetworkingConfiguration): HashMap<String, String> {
        val data: HashMap<String, String> = HashMap()
        if (bcpNetworkingConfiguration.bodyType == NetworkingBody.FORMURLENCODED &&
            bcpNetworkingConfiguration.body is HashMap<*, *>
        ) {
            val body = bcpNetworkingConfiguration.body as? HashMap<*, *>

            body?.let {
                for (line in body.keys) {
                    val key = line as String
                    val value = body[line] as String
                    data[key] = value
                }
            }
        }
        return data
    }
}