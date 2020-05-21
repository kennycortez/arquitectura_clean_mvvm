package com.example.data.network

import com.example.helper.error.Failure
import java.net.ConnectException

object ErrorUtil {

    fun handler(t: Throwable?): Failure {
        return when(t){
            is ConnectException -> Failure.NetworkConnection
            else -> Failure.DefaultError
        }
    }

}