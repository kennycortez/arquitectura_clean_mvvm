package com.example.helper.util





object ErrorUtil {

   /* fun errorHandler(error: Throwable): DefaultError {

        val errorException: RetrofitException =
            if (error is RetrofitException) {
                error
            } else {
                RetrofitException.retrofitException(error)
            }

        return when (errorException.kind) {
            RetrofitException.Kind.HTTP -> errorException.getErrorBodyAs(DefaultError::class.java)!!
            RetrofitException.Kind.NETWORK -> DefaultError()
            else -> DefaultError()
        }
    }*/
}