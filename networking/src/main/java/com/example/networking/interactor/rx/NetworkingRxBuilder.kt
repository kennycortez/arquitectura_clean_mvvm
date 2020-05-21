package com.example.networking.interactor.rx

import com.example.networking.model.NetworkingConfiguration
import com.example.networking.util.NetworkingHttpVerb
import io.reactivex.Single
import retrofit2.Response

/**
 * Middle class between BCPNetworkingManager and BCPNetworkingRxInteractor
 *
 * @param bcpNetworkingConfiguration module configuration
 */
class NetworkingRxBuilder(
    private val bcpNetworkingConfiguration: NetworkingConfiguration
) {

    /**
     * Depending to the httpVerb (BCPNetworkingConfiguration), notify BCPNetworkingRxInteractor
     */
    fun connect(): Single<Response<Any>> {

        return when (bcpNetworkingConfiguration.httpVerb) {
            NetworkingHttpVerb.GET -> NetworkingRxInteractor.GET.execute(bcpNetworkingConfiguration)
            NetworkingHttpVerb.POST -> NetworkingRxInteractor.POST.execute(bcpNetworkingConfiguration)
            NetworkingHttpVerb.PUT -> NetworkingRxInteractor.PUT.execute(bcpNetworkingConfiguration)
            NetworkingHttpVerb.PATCH -> NetworkingRxInteractor.PATCH.execute(bcpNetworkingConfiguration)
            NetworkingHttpVerb.DELETE -> NetworkingRxInteractor.DELETE.execute(bcpNetworkingConfiguration)
            else -> NetworkingRxInteractor.GET.execute(bcpNetworkingConfiguration)
        }
    }
}