package com.example.networking.interactor.coroutine.json

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*

interface CoroutineJsonService {

    /**
     *  Template get request
     *
     *  @param @Url concatenated endpoint + get params
     *  @return any object
     */
    @GET
    fun get(
        @Url url: String
    ): Deferred<Response<Any>>

    /**
     *  Template post request
     *
     *  @param @Url: url base + endpoint
     *  @param @Body payload
     *  @return any object
     */
    @POST
    fun post(
        @Url url: String,
        @Body body: Any?
    ): Deferred<Response<Any>>

    /**
     *  Template delete request
     *
     *  @param @Url: url base + endpoint
     *  @return any object
     */
    @DELETE
    fun delete(
        @Url url: String
    ): Deferred<Response<Any>>

    /**
     *  Template put request
     *
     *  @param @Url: url base + endpoint
     *  @param @Body payload
     *  @return any object
     */
    @PUT
    fun put(
        @Url url: String,
        @Body body: Any?
    ): Deferred<Response<Any>>

    /**
     *  Template patch request
     *
     *  @param @Url: url base + endpoint
     *  @param @Body payload
     *  @return any object
     */
    @PATCH
    fun patch(
        @Url url: String,
        @Body body: Any?
    ): Deferred<Response<Any>>
}