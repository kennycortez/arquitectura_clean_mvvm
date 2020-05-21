package com.example.networking.interactor.rx.formurlencoded

import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*

interface RxFormUrlEncodedService {

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
     *  @param @FieldMap payload
     *  @return any object
     */
    @FormUrlEncoded
    @POST
    fun post(
        @Url url: String,
        @FieldMap body: HashMap<String, String>
    ): Single<Response<Any>>

    /**
     *  Template put request
     *
     *  @param @Url: url base + endpoint
     *  @param @FieldMap payload
     *  @return any object
     */
    @FormUrlEncoded
    @PUT
    fun put(
        @Url url: String,
        @FieldMap body: HashMap<String, String>
    ): Single<Response<Any>>

    /**
     *  Template patch request
     *
     *  @param @Url: url base + endpoint
     *  @param @FieldMap payload
     *  @return any object
     */
    @FormUrlEncoded
    @PATCH
    fun patch(
        @Url url: String,
        @FieldMap body: HashMap<String, String>
    ): Single<Response<Any>>

    /**
     *  Template get request
     *
     *  @param @Url: url base + endpoint
     *  @return any object
     */
    @GET
    fun delete(
        @Url url: String
    ): Single<Response<Any>>
}