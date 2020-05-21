package com.example.helper.base

import com.example.helper.error.Failure
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by junocc on 2019-05-15
 */

abstract class UseCase<out Type, in Params> where Type : Any {

    abstract suspend fun run(params: Params): ResultType<Failure, Type>

    suspend operator fun invoke(params: Params, onResult: (ResultType<Failure, Type>) -> Unit = {}) {
        val result = withContext(Dispatchers.IO) {
            run(params)
        }

        onResult(result)
    }

}