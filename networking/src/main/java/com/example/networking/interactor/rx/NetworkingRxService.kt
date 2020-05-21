package com.example.networking.interactor.rx

import android.annotation.SuppressLint
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

/**
 *  Validate if the answer is satisfactory or there was an error
 */
object NetworkingRxService {

    /**
     * Notify if the request was successful or there was a controlled server error
     */
    @SuppressLint("CheckResult")
    fun execute(single: Single<Response<Any>>): Single<Response<Any>> {

        single.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        return single
    }
}