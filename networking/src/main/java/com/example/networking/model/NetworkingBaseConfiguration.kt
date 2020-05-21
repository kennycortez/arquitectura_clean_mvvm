package com.example.networking.model

import com.example.networking.util.NetworkingBody
import com.example.networking.util.NetworkingType

class NetworkingBaseConfiguration private constructor(
    /* url base*/
    var baseUrl: String?,
    /* timeout in minutes */
    var timeout: Long,
    /* type for the moment Coroutines and Rx */
    var type: NetworkingType?,
    /* header */
    var header: Map<String, String>?,
    /* use pinning */
    var mutual: Boolean?,
    /* body type */
    var bodyType: NetworkingBody?
) {

    data class BCPNetworkingBaseConfigurationBuilder(
        var baseUrl: String? = null,
        var timeout: Long = 1,
        var type: NetworkingType? = null,
        var header: Map<String, String>? = null,
        var mutual: Boolean = false,
        var bodyType: NetworkingBody = NetworkingBody.JSON
    ) {

        fun baseUrl(baseUrl: String) = apply { this.baseUrl = baseUrl }
        fun timeout(minutes: Long) = apply { this.timeout = minutes }
        fun type(type: NetworkingType) = apply { this.type = type }
        fun header(header: Map<String, String>) = apply { this.header = header }
        fun mutual(mutual: Boolean) = apply { this.mutual = mutual }
        fun bodyType(bodyType: NetworkingBody) = apply { this.bodyType = bodyType }

        fun build() = NetworkingBaseConfiguration(
            baseUrl,
            timeout,
            type,
            header,
            mutual,
            bodyType
        )
    }
}