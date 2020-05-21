package com.example.networking.model

import com.example.networking.util.NetworkingBody
import com.example.networking.util.NetworkingHttpVerb
import com.example.networking.util.NetworkingType

/**
 *  Initial configuration class
 */
class NetworkingConfiguration private constructor(
    /* url base*/
    var baseUrl: String?,
    /* timeout in minutes */
    var timeout: Long?,
    /* endpoint */
    var endpoint: String?,
    /* http verb  */
    var httpVerb: NetworkingHttpVerb? = null,
    /* type for the moment Coroutines and RX */
    var type: NetworkingType? = null,
    /* body for POST, PUT, PATCH */
    var body: Any?,
    /* header */
    var header: Map<String, String>? = null,
    /* use mutual authentication */
    var mutual: Boolean?,
    /* body type */
    var bodyType: NetworkingBody? = null
) {

    data class CPNetworkingConfigurationBuilder(
        var baseUrl: String? = null,
        var timeout: Long? = 1,
        var endpoint: String? = null,
        var httpVerb: NetworkingHttpVerb? = null,
        var type: NetworkingType? = null,
        var body: Any? = null,
        var header: Map<String, String>? = null,
        var mutual: Boolean? = null,
        var bodyType: NetworkingBody? = null
    ) {

        fun baseUrl(baseUrl: String?) = apply { this.baseUrl = baseUrl }
        fun timeout(minutes: Long?) = apply { this.timeout = minutes }
        fun endpoint(endpoint: String?) = apply { this.endpoint = endpoint }
        fun type(type: NetworkingType?) = apply { this.type = type }
        fun httpVerb(httpVerb: NetworkingHttpVerb?) = apply { this.httpVerb = httpVerb }
        fun body(body: Any?) = apply { this.body = body }
        fun header(header: Map<String, String>?) = apply { this.header = header }
        fun mutual(mutual: Boolean?) = apply { this.mutual = mutual }
        fun bodyType(bodyType: NetworkingBody?) = apply { this.bodyType = bodyType }

        fun config() = NetworkingConfiguration(
            baseUrl,
            timeout,
            endpoint ?: error("Endpoint es required"),
            httpVerb,
            type,
            body,
            header,
            mutual,
            bodyType
        )
    }
}