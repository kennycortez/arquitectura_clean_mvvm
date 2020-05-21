package com.example.networking.model

class Networking private constructor(
    /* certificate pwd*/
    var pwd: CharArray?,
    /* certificate in string */
    var certificate: String?,
    /* BCPNetworkingBaseConfiguration init */
    var bcpNetworkingBaseConfiguration: NetworkingBaseConfiguration?
) {

    data class BCPNetworkingBuilder(
        var pwd: CharArray? = null,
        var certificate: String? = null,
        var bcpNetworkingBaseConfiguration: NetworkingBaseConfiguration? = null
    ) {

        fun pwd(pwd: CharArray) = apply { this.pwd = pwd }
        fun certificate(certificate: String) = apply { this.certificate = certificate }
        fun bcpNetworkingBaseConfiguration(bcpNetworkingBaseConfiguration: NetworkingBaseConfiguration) =
            apply { this.bcpNetworkingBaseConfiguration = bcpNetworkingBaseConfiguration }

        fun build() = Networking(
            pwd,
            certificate,
            bcpNetworkingBaseConfiguration
        )
    }
}