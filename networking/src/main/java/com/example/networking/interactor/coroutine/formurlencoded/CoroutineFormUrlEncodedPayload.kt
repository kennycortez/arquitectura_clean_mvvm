package com.example.networking.interactor.coroutine.formurlencoded

import com.example.networking.NetworkingManager
import com.example.networking.interactor.coroutine.CoroutinePayload
import com.example.networking.model.NetworkingConfiguration
import com.example.networking.util.NetworkingHttpVerb
import com.example.networking.util.BodyUtil
import okhttp3.HttpUrl
import retrofit2.Response

/**
 * Class with implements BCPCoroutinePayload and return a response or error
 */
class CoroutineFormUrlEncodedPayload : CoroutinePayload {

    @Throws(Throwable::class)
    override suspend fun payload(bcpNetworkingConfiguration: NetworkingConfiguration): Response<Any> {

        val response = NetworkingManager
            .connect(CoroutineFormUrlEncodedService::class.java, bcpNetworkingConfiguration)

        return when (bcpNetworkingConfiguration.httpVerb) {
            NetworkingHttpVerb.GET -> response.get(
                url(bcpNetworkingConfiguration.baseUrl, bcpNetworkingConfiguration.endpoint)
            ).await()
            NetworkingHttpVerb.DELETE -> response.delete(
                url(
                    bcpNetworkingConfiguration.baseUrl,
                    bcpNetworkingConfiguration.endpoint
                )
            ).await()
            NetworkingHttpVerb.PATCH -> response.patch(
                url(bcpNetworkingConfiguration.baseUrl, bcpNetworkingConfiguration.endpoint),
                BodyUtil.getBodyFormUrlEncoded(bcpNetworkingConfiguration)
            ).await()
            NetworkingHttpVerb.PUT -> response.put(
                url(bcpNetworkingConfiguration.baseUrl, bcpNetworkingConfiguration.endpoint),
                BodyUtil.getBodyFormUrlEncoded(bcpNetworkingConfiguration)
            ).await()
            else -> response.post(
                url(bcpNetworkingConfiguration.baseUrl, bcpNetworkingConfiguration.endpoint),
                BodyUtil.getBodyFormUrlEncoded(bcpNetworkingConfiguration)
            ).await()
        }
    }

    override fun showError(): String = "For FormUrlEncoded BodyType, payload HashMap is required"

    override fun url(baseUrl: String?, endpoint: String?) = HttpUrl.parse(baseUrl.plus(endpoint)).toString()
}