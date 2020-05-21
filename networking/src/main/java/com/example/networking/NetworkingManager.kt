package com.example.networking

import com.example.networking.interactor.coroutine.NetworkingCoroutineBuilder
import com.example.networking.interactor.rx.NetworkingRxBuilder
import com.example.networking.model.Networking
import com.example.networking.model.NetworkingConfiguration
import com.example.networking.util.NetworkingHttpVerb
import com.example.networking.util.NetworkingType
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.LinkedHashMap
import java.util.concurrent.TimeUnit

/***
 * Class that handles the initial configuration of the module
 ***/
@SuppressWarnings("StaticFieldLeak")
object NetworkingManager {

    private var bcpNetworking: Networking? = null

    /**
     *  Retrofit instance
     *
     * @param bcpNetworkingConfiguration module configuration
     * @param apiService Retrofit needs this interface to initialize
     */
    private fun <T> createService(bcpNetworkingConfiguration: NetworkingConfiguration, apiService: Class<T>): T {
        return retrofit(bcpNetworkingConfiguration)!!.create(apiService)
    }

    fun retrofit(bcpNetworkingConfiguration: NetworkingConfiguration): Retrofit? {
        bcpNetworkingConfiguration.baseUrl?.let {
            val baseUrl = bcpNetworkingConfiguration.baseUrl

            val retrofitBuilder = Retrofit.Builder()
                .baseUrl(baseUrl!!)
                .addConverterFactory(GsonConverterFactory.create())

            if (bcpNetworkingConfiguration.type == NetworkingType.COROUTINES) {
                retrofitBuilder.addCallAdapterFactory(CoroutineCallAdapterFactory())
            } else {
                retrofitBuilder.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            }

            retrofitBuilder.client(getOkHttpClient(bcpNetworkingConfiguration))
            return retrofitBuilder.build()
        }
        return null
    }

    /**
     * OKHttpClient instance
     *
     * @param bcpNetworkingConfiguration module configuration
     * @return OkHttpClient instance
     */
    private fun getOkHttpClient(
        bcpNetworkingConfiguration: NetworkingConfiguration
    ): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()


        httpLoggingInterceptor.level = if (BuildConfig.DEBUG)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE


        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        var okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()

        if (bcpNetworkingConfiguration.mutual!!) {
            val pwd = this.bcpNetworking?.pwd
            val certificate = this.bcpNetworking?.certificate

            if (pwd == null) error("PWD is required")
            if (certificate == null) error("Certificate is required")

         /*   val secureOkHttpClient =
                SecureOkHttpClient.getSecureOkHttpClient(pwd, certificate)
                    .build()
            okHttpClientBuilder = secureOkHttpClient.newBuilder()*/
        }

        okHttpClientBuilder.connectTimeout(bcpNetworkingConfiguration.timeout!!, TimeUnit.MINUTES)
        okHttpClientBuilder.readTimeout(bcpNetworkingConfiguration.timeout!!, TimeUnit.MINUTES)

        val header = bcpNetworkingConfiguration.header

        if (header != null && header.isNotEmpty()) {
            okHttpClientBuilder.addInterceptor(getInterceptor(header))
        }

        okHttpClientBuilder.addInterceptor(httpLoggingInterceptor)

        return okHttpClientBuilder.build()
    }

    /**
     * Add headers in interceptor
     *
     * @param header map
     */
    private fun getInterceptor(header: Map<String, String>?): Interceptor {

        return Interceptor { chain ->
            var request = chain.request()

            if (header != null && header.isNotEmpty()) {
                for ((key, value) in header) {
                    request = request.newBuilder().addHeader(key, value).build()
                }
            }

            val originalResponse = chain.proceed(request)
            originalResponse.newBuilder().build()
        }
    }

    /**
     * Call the builder to establish the connection with Coroutines
     *
     * @param bcpNetworkingConfiguration module configuration
     * @return BCPNetworkingCoroutineBuilder instance
     */
    fun with(
        bcpNetworkingConfiguration: NetworkingConfiguration
    ): NetworkingCoroutineBuilder = NetworkingCoroutineBuilder(
        validate(
            replaceBaseConfiguration(bcpNetworkingConfiguration)
        )
    )

    /**
     * Call the builder to establish the connection with RX
     *
     * @param bcpNetworkingConfiguration module configuration
     * @return BCPNetworkingRxBuilder instance
     */
    fun get(
        bcpNetworkingConfiguration: NetworkingConfiguration
    ): NetworkingRxBuilder = NetworkingRxBuilder(
        validate(
            replaceBaseConfiguration(bcpNetworkingConfiguration)
        )
    )

    /**
     *  Set values from BcpNetworkingBaseConfiguration to BCPNetworkingConfiguration and show errors
     *  of required parameters
     *
     *  Verb http or the body isn't sent, GET is default
     *  Verb http isn't sent but if the body, POST is default
     */
    private fun validate(bcpNetworkingConfiguration: NetworkingConfiguration): NetworkingConfiguration {

        if (bcpNetworkingConfiguration.baseUrl == null) {
            error("Url base is required")
        }

        if (bcpNetworkingConfiguration.type == null) {
            error("BCPNetworkingType is required")
        }

        if (bcpNetworkingConfiguration.mutual == null) {
            bcpNetworkingConfiguration.mutual = false
        }

        if (bcpNetworkingConfiguration.httpVerb == null && bcpNetworkingConfiguration.body == null) {
            bcpNetworkingConfiguration.httpVerb = NetworkingHttpVerb.GET
        }

        if (bcpNetworkingConfiguration.httpVerb == null && bcpNetworkingConfiguration.body != null) {
            bcpNetworkingConfiguration.httpVerb = NetworkingHttpVerb.POST
        }

        return bcpNetworkingConfiguration
    }

    /**
     * Start the module configuration
     *
     * @param apiService Retrofit needs this interface to initialize
     * @param bcpNetworkingConfiguration module configuration
     */
    fun <T> connect(
        apiService: Class<T>,
        bcpNetworkingConfiguration: NetworkingConfiguration
    ): T {
        return createService(bcpNetworkingConfiguration, apiService)
    }

    /**
     *  Set values from BCPNetworkingBaseConfiguration to BCPNetworkingConfiguration
     */
    private fun replaceBaseConfiguration(
        bcpNetworkingConfiguration: NetworkingConfiguration
    ): NetworkingConfiguration {

        val networkingConfiguration = NetworkingConfiguration.CPNetworkingConfigurationBuilder()

        if (bcpNetworking?.bcpNetworkingBaseConfiguration != null) {
            networkingConfiguration.baseUrl(bcpNetworking?.bcpNetworkingBaseConfiguration?.baseUrl)
                .timeout(bcpNetworking?.bcpNetworkingBaseConfiguration?.timeout)
                .type(bcpNetworking?.bcpNetworkingBaseConfiguration?.type)
                .mutual(bcpNetworking?.bcpNetworkingBaseConfiguration?.mutual)
                .bodyType(bcpNetworking?.bcpNetworkingBaseConfiguration?.bodyType)

            if (bcpNetworking?.bcpNetworkingBaseConfiguration?.header == null) {
                networkingConfiguration.header = bcpNetworking?.bcpNetworkingBaseConfiguration?.header
            } else if (!bcpNetworking?.bcpNetworkingBaseConfiguration?.header.isNullOrEmpty()) {
                val header = LinkedHashMap<String, String>()
                bcpNetworking?.bcpNetworkingBaseConfiguration?.header!!.forEach { (key, value) ->
                    header[key] = value
                }
                /*
                networkingConfiguration.header!!.forEach { (key, value) ->
                    header[key] = value
                }
                */
                networkingConfiguration.header(header)
            }
        }

        if (bcpNetworkingConfiguration.baseUrl != null) {
            networkingConfiguration.baseUrl(bcpNetworkingConfiguration.baseUrl)
        }

        if (bcpNetworkingConfiguration.timeout != null) {
            networkingConfiguration.timeout(bcpNetworkingConfiguration.timeout)
        }

        if (bcpNetworkingConfiguration.type != null) {
            networkingConfiguration.type(bcpNetworkingConfiguration.type)
        }

        if (bcpNetworkingConfiguration.mutual != null) {
            networkingConfiguration.mutual(bcpNetworkingConfiguration.mutual)
        }

        if (bcpNetworkingConfiguration.bodyType != null) {
            networkingConfiguration.bodyType(bcpNetworkingConfiguration.bodyType)
        }

        networkingConfiguration.endpoint(bcpNetworkingConfiguration.endpoint)
            .httpVerb(bcpNetworkingConfiguration.httpVerb)
            .body(bcpNetworkingConfiguration.body)
            .header(bcpNetworkingConfiguration.header)

        return networkingConfiguration.config()
    }

    fun init(bcpNetworking: Networking) {
        this.bcpNetworking = bcpNetworking
    }
}