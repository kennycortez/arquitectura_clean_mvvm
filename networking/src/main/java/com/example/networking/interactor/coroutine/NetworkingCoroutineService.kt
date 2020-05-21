package com.example.networking.interactor.coroutine

import com.example.networking.service.ResultService
import com.google.gson.JsonParser
import retrofit2.Response

/**
 *  Validate if the answer is satisfactory or there was an error
 */
object NetworkingCoroutineService {

    private const val CODE_204 = 204

    /**
     * Notify if the request was successful or there was a controlled server error
     */
    fun execute(response: Response<Any>): ResultService {
        return if (response.isSuccessful) {
            var model: Any? = null

            if (response.code() != CODE_204) {
                model = response.body()
            }

            ResultService(true, model, null,response.code())
        } else {
            val error = response.errorBody()!!.string()
            val parser = JsonParser()
            val jsonObject = parser.parse(error).asJsonObject

            ResultService(false, null, jsonObject,response.code())
        }
    }
}