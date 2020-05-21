package com.example.helper.base

/**
 * Created by junocc on 2019-06-06
 */


sealed class ResultType<out F, out R> {
    /** * Represents the left side of [Either] class which by convention is a "Failure". */
    data class Failure<out F>(val a: F) : ResultType<F, Nothing>()
    /** * Represents the right side of [Either] class which by convention is a "Success". */
    data class Success<out R>(val b: R) : ResultType<Nothing, R>()

    val isSuccess get() = this is Success<R>
    val isFailure get() = this is Failure<F>

    fun <L> failure(a: L) = Failure(a)
    fun <R> success(b: R) = Success(b)

    fun either(fnL: (F) -> Any, fnR: (R) -> Any): Any =
        when (this) {
            is Failure -> fnL(a)
            is Success -> fnR(b)
        }
}