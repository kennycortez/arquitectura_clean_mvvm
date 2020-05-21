package com.example.helper.util


data class DefaultError(
    var message: String
) {
    constructor() : this("Ocurrió un error...")
}