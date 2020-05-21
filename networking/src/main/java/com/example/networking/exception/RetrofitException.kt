package com.example.networking.exception

import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit
import java.io.IOException

class RetrofitException internal constructor(
    message: String?,
    private val url: String?,
    private val response: Response<*>?,
    val kind: Kind,
    exception: Throwable?,
    val retrofit: Retrofit?
) : RuntimeException(message, exception) {

    enum class Kind {
        NETWORK,
        HTTP,
        UNEXPECTED
    }

    @Throws(IOException::class)
    fun <T> getErrorBodyAs(type: Class<T>): T? {
        val converter = retrofit?.responseBodyConverter<T>(type, arrayOfNulls(0))
        return converter?.convert(response?.errorBody()!!)
    }

    companion object {

        private const val DEFAULT_ERROR = "Ocurrió un error"

        private fun httpError(url: String, response: Response<*>, retrofit: Retrofit?): RetrofitException {
            val message = response.code().toString() + " " + response.message()
            return RetrofitException(
                message,
                url,
                response,
                Kind.HTTP,
                null,
                retrofit
            )
        }

        private fun networkError(exception: IOException, retrofit: Retrofit?): RetrofitException {
            return RetrofitException(
                exception.message,
                null,
                null,
                Kind.NETWORK,
                exception,
                retrofit
            )
        }

        private fun unexpectedError(exception: Throwable, retrofit: Retrofit?): RetrofitException {
            return RetrofitException(
                DEFAULT_ERROR,
                null,
                null,
                Kind.UNEXPECTED,
                exception,
                retrofit
            )
        }

        fun retrofitException(throwable: Throwable): RetrofitException {

            if (throwable is HttpException) {
                val response = throwable.response()
                return httpError(
                    response.raw().request().url().toString(),
                    response,
                    null
                )
            }

            return if (throwable is IOException) {
                networkError(throwable, null)
            } else unexpectedError(throwable, null)
        }
    }
}