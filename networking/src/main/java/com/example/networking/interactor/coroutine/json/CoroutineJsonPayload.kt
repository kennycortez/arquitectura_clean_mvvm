package com.example.networking.interactor.coroutine.json

import com.example.networking.NetworkingManager
import com.example.networking.interactor.coroutine.CoroutinePayload
import com.example.networking.model.NetworkingConfiguration
import com.example.networking.util.NetworkingHttpVerb
import okhttp3.HttpUrl
import retrofit2.Response

/**
 * Class with implements BCPCoroutinePayload and return a response or error
 */
class CoroutineJsonPayload : CoroutinePayload {

    @Throws(Throwable::class)
    override suspend fun payload(bcpNetworkingConfiguration: NetworkingConfiguration): Response<Any> {
        val response = NetworkingManager
            .connect(CoroutineJsonService::class.java, bcpNetworkingConfiguration)

        return when (bcpNetworkingConfiguration.httpVerb) {
            NetworkingHttpVerb.GET -> response.get(
                url(bcpNetworkingConfiguration.baseUrl, bcpNetworkingConfiguration.endpoint)
            ).await()
            NetworkingHttpVerb.DELETE -> response.delete(
                url(bcpNetworkingConfiguration.baseUrl, bcpNetworkingConfiguration.endpoint)
            ).await()
            NetworkingHttpVerb.PUT -> response.put(
                url(bcpNetworkingConfiguration.baseUrl, bcpNetworkingConfiguration.endpoint),
                bcpNetworkingConfiguration.body!!
            ).await()
            NetworkingHttpVerb.PATCH -> response.patch(
                url(bcpNetworkingConfiguration.baseUrl, bcpNetworkingConfiguration.endpoint),
                bcpNetworkingConfiguration.body!!
            ).await()
            else -> response.post(
                url(bcpNetworkingConfiguration.baseUrl, bcpNetworkingConfiguration.endpoint),
                bcpNetworkingConfiguration.body!!
            ).await()
        }
    }

    override fun showError(): String = "For Json BodyType, payload Json is required"

    override fun url(baseUrl: String?, endpoint: String?) = HttpUrl.parse(baseUrl.plus(endpoint)).toString()
}