package com.example.helper.error

sealed class Failure{

    object NetworkConnection : Failure()
    object DefaultError: Failure()

    data class ServerError(val error: Any?) : Failure()

    /** * Extend this class for feature specific failures.*/
    abstract class FeatureFailure : Failure()

}