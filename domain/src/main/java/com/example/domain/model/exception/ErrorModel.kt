package com.example.domain.model.exception

data class ErrorModel (

    val code: String,

    val description: String?=null,

    val title: String?=null,

    val statusCode: Int? = null

)