package com.example.networking.interactor.coroutine

import com.example.networking.model.NetworkingConfiguration
import retrofit2.Response

/**
 * Interface they will implement to get a response or error
 */
interface CoroutinePayload {

    /**
     *  Return response
     *
     *  @param bcpNetworkingConfiguration module configuration
     */
    @Throws(Throwable::class)
    suspend fun payload(bcpNetworkingConfiguration: NetworkingConfiguration): Response<Any>

    /**
     *  Show error
     */
    fun showError(): String

    /**
     * Concatenate url base + endpoint
     */
    fun url(baseUrl: String?, endpoint: String?): String
}