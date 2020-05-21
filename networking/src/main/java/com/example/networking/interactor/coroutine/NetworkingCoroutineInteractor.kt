package com.example.networking.interactor.coroutine

import com.example.networking.model.NetworkingConfiguration
import com.example.networking.service.ResultService
import com.example.networking.interactor.coroutine.formurlencoded.CoroutineFormUrlEncodedPayload
import com.example.networking.interactor.coroutine.json.CoroutineJsonPayload
import com.example.networking.util.NetworkingBody

/**
 * Class that makes calls to the server
 */
enum class NetworkingCoroutineInteractor {

    /* get http verb */
    GET {

        @Throws(Throwable::class)
        override suspend fun execute(bcpNetworkingConfiguration: NetworkingConfiguration): ResultService {
            return NetworkingCoroutineService.execute(
                payloadCoroutineType(bcpNetworkingConfiguration.bodyType!!).payload(bcpNetworkingConfiguration)
            )
        }
    },

    /* post http verb */
    POST {
        @Throws(Throwable::class)
        override suspend fun execute(bcpNetworkingConfiguration: NetworkingConfiguration): ResultService {
            return NetworkingCoroutineService.execute(
                payloadCoroutineType(bcpNetworkingConfiguration.bodyType!!).payload(bcpNetworkingConfiguration)
            )
        }
    },

    /* put http verb */
    PUT {
        @Throws(Throwable::class)
        override suspend fun execute(bcpNetworkingConfiguration: NetworkingConfiguration): ResultService {
            return NetworkingCoroutineService.execute(
                payloadCoroutineType(bcpNetworkingConfiguration.bodyType!!).payload(bcpNetworkingConfiguration)
            )
        }
    },

    /* patch http verb */
    PATCH {
        @Throws(Throwable::class)
        override suspend fun execute(bcpNetworkingConfiguration: NetworkingConfiguration): ResultService {
            return NetworkingCoroutineService.execute(
                payloadCoroutineType(bcpNetworkingConfiguration.bodyType!!).payload(bcpNetworkingConfiguration)
            )
        }
    },

    /* delete http verb */
    DELETE {
        @Throws(Throwable::class)
        override suspend fun execute(bcpNetworkingConfiguration: NetworkingConfiguration): ResultService {
            return NetworkingCoroutineService.execute(
                payloadCoroutineType(bcpNetworkingConfiguration.bodyType!!).payload(bcpNetworkingConfiguration)
            )
        }
    };

    /**
     *  Execute the call to the server
     *
     *  @param bcpNetworkingConfiguration module configuration
     */
    @Throws(Throwable::class)
    internal abstract suspend fun execute(bcpNetworkingConfiguration: NetworkingConfiguration): ResultService

    /**
     *  Validate bodyType
     *
     *  @param bcpNetworkingConfiguration module configuration
     *  @return BCPCoroutinePayload with Factory pattern
     */
    @Throws(Throwable::class)
    protected fun payloadCoroutineType(bodyType: NetworkingBody): CoroutinePayload {
        return when (bodyType) {
            NetworkingBody.JSON -> CoroutineJsonPayload()
            NetworkingBody.FORMURLENCODED -> CoroutineFormUrlEncodedPayload()
        }
    }
}