package com.example.networking.credential

import com.example.networking.util.InputStreamUtil
import com.example.networking.util.LogUtil
import okhttp3.OkHttpClient
import java.io.IOException
import java.io.InputStream
import java.security.KeyStore
import java.security.KeyStoreException
import java.security.NoSuchAlgorithmException
import java.security.cert.CertificateException
import java.util.*
import javax.net.ssl.*

object SecureOkHttpClient {

    private lateinit var pwd: CharArray
    private lateinit var certificate: String
    private var instanceType: String = "PKCS12"
    private var keyAlgorithm: String = "X509"

    /**
     *  Create a connection object that implements Mutual Authentication.
     * @param keyAlgorithm algorithm that KeyManager will use
     * @param sslProtocol protocol that SSLContext will use
     * @param instanceType instance for default PKCS12
     */
    @Throws(IOException::class)
    fun getSecureOkHttpClient(
        pwd: CharArray,
        certificate: String,
        keyAlgorithm: String = "X509",
        sslProtocol: String = "TLSv1.2",
        instanceType: String = "PKCS12"
    ): OkHttpClient.Builder {

        try {
            this.pwd = pwd
            this.certificate = certificate
            this.instanceType = instanceType
            this.keyAlgorithm = keyAlgorithm

            val keyManagerFactory = KeyManagerFactory.getInstance(keyAlgorithm)
            val keyStore = readKeyStore(certificate(), instanceType)
            keyManagerFactory.init(keyStore, pwd)
            val keyManagers = keyManagerFactory.keyManagers

            val trustManagerFactory = TrustManagerFactory.getInstance(
                TrustManagerFactory.getDefaultAlgorithm()
            )
            trustManagerFactory.init(null as KeyStore?)
            val trustManagers = trustManagerFactory.trustManagers
            if (trustManagers.size != 1 || trustManagers[0] !is X509TrustManager) {
                throw IllegalStateException("Unexpected default trust managers:" + Arrays.toString(trustManagers))
            }
            val trustManager = trustManagers[0] as X509TrustManager

            val sslContext = SSLContext.getInstance(sslProtocol)
            sslContext.init(keyManagers, arrayOf<TrustManager>(trustManager), null)
            val sslSocketFactory = sslContext.socketFactory

            return OkHttpClient.Builder().sslSocketFactory(sslSocketFactory, trustManager)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    /**
     *  Read the keystore with the indicated parameters
     */
    @Throws(IOException::class)
    private fun readKeyStore(certificate: InputStream, instanceType: String): KeyStore? {
        var keyStore: KeyStore? = null
        var inputStream: InputStream? = null
        try {
            keyStore = KeyStore.getInstance(instanceType)
            inputStream = certificate
            keyStore!!.load(inputStream, pwd)
        } catch (e: KeyStoreException) {
            LogUtil.e("KeyStoreException", e.toString())
        } catch (e: NoSuchAlgorithmException) {
            LogUtil.e("NoSuchAlgorithmException", e.toString())
        } catch (e: IOException) {
            LogUtil.e("IOException", e.toString())
        } catch (e: CertificateException) {
            LogUtil.e("CertificateException", e.toString())
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close()
                } catch (e: IOException) {
                    LogUtil.e("IOException", e.toString())
                }
            }
        }
        return keyStore
    }

    private fun certificate(): InputStream {

        try {
            return InputStreamUtil.stringToInputStream(certificate)
        } catch (exception: Exception) {
            LogUtil.e("z- exception", exception.toString())
            error("Cannot read certificate")
        }
    }
}