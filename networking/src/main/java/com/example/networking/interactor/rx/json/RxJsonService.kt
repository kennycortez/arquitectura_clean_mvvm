package com.example.networking.interactor.rx.json

import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*

interface RxJsonService {

    /**
     *  Template get request
     *
     *  @param @Url concatenated endpoint + get params
     *  @return any object
     */
    @GET
    fun get(
        @Url url: String
    ): Single<Response<Any>>

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
    ): Single<Response<Any>>

    /**
     *  Template delete request
     *
     *  @param @Url: url base + endpoint
     *  @return any object
     */
    @DELETE
    fun delete(
        @Url url: String
    ): Single<Response<Any>>

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
    ): Single<Response<Any>>

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
    ): Single<Response<Any>>
}