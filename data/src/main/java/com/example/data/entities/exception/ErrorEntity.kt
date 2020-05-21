package com.example.data.entities.exception

import com.google.gson.annotations.SerializedName

data class ErrorEntity(

    @SerializedName("code")
    val code: String,

    @SerializedName("description")
    val description: String?,

    @SerializedName("errorType")
    val errorType: String?,

    @SerializedName("errorProperties")
    val errorProperties: ErrorProperties,

    @SerializedName("statusCode")
    var statusCode: Int?

)