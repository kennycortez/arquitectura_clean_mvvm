package com.example.networking.interactor.rx.json

import com.example.networking.NetworkingManager
import com.example.networking.interactor.rx.RxPayload
import com.example.networking.model.NetworkingConfiguration
import com.example.networking.util.NetworkingHttpVerb
import io.reactivex.Single
import okhttp3.HttpUrl
import retrofit2.Response

/**
 * Class with implements BCPRxPayload and return a response or error
 */
class RxJsonPayload : RxPayload {

    @Throws(Throwable::class)
    override fun payload(bcpNetworkingConfiguration: NetworkingConfiguration): Single<Response<Any>> {
        val response = NetworkingManager
            .connect(RxJsonService::class.java, bcpNetworkingConfiguration)

        return when (bcpNetworkingConfiguration.httpVerb) {
            NetworkingHttpVerb.GET -> response.get(
                url(
                    bcpNetworkingConfiguration.baseUrl,
                    bcpNetworkingConfiguration.endpoint
                )
            )
            NetworkingHttpVerb.DELETE -> response.delete(
                url(
                    bcpNetworkingConfiguration.baseUrl,
                    bcpNetworkingConfiguration.endpoint
                )
            )
            NetworkingHttpVerb.PUT -> response.put(
                url(
                    bcpNetworkingConfiguration.baseUrl,
                    bcpNetworkingConfiguration.endpoint
                ),
                bcpNetworkingConfiguration.body!!
            )
            NetworkingHttpVerb.PATCH -> response.patch(
                url(
                    bcpNetworkingConfiguration.baseUrl,
                    bcpNetworkingConfiguration.endpoint
                ),
                bcpNetworkingConfiguration.body!!
            )
            else -> response.post(
                url(
                    bcpNetworkingConfiguration.baseUrl,
                    bcpNetworkingConfiguration.endpoint
                ), bcpNetworkingConfiguration.body!!
            )
        }
    }

    override fun showError(): String = "For Json BodyType, payload Json is required"

    override fun url(baseUrl: String?, endpoint: String?) = HttpUrl.parse(baseUrl.plus(endpoint)).toString()
}