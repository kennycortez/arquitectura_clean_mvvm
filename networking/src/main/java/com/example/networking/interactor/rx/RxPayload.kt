package com.example.networking.interactor.rx

import com.example.networking.model.NetworkingConfiguration
import io.reactivex.Single
import retrofit2.Response

/**
 * Interface they will implement to get a response or error
 */
interface RxPayload {

    /**
     *  Return Single
     *
     *  @param bcpNetworkingConfiguration module configuration
     */
    @Throws(Throwable::class)
    fun payload(bcpNetworkingConfiguration: NetworkingConfiguration): Single<Response<Any>>

    /**
     *  Show error
     */
    fun showError(): String

    /**
     * Concatenate url base + endpoint
     */
    fun url(baseUrl: String?, endpoint: String?): String
}