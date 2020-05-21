package com.example.data.entities.exception


import com.google.gson.annotations.SerializedName

data class ErrorProperties(

    @SerializedName("tag")
    val tag: String,

    @SerializedName("title")
    val title: String

)
