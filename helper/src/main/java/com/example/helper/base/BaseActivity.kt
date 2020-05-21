package com.example.helper.base

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.helper.BuildConfig
import com.example.helper.themes.ThemeManager
import com.example.helper.util.CertificateUtil
import com.example.networking.NetworkingManager
import com.example.networking.model.Networking
import com.example.networking.model.NetworkingBaseConfiguration
import com.example.networking.util.NetworkingType
import com.example.networking.util.InputStreamUtil

/**
 * Created by junocc on 2019-05-17
 *
 */

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ThemeManager.setTheme(this)
        initNetworkingConfiguration()
    }

    open fun showProgress() {
        window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }

    open fun hideProgress() {
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }

    private fun initNetworkingConfiguration() {



     /*   val bcpNetworkingConfiguration = BCPNetworkingBaseConfiguration.BCPNetworkingBaseConfigurationBuilder()
            .baseUrl(BuildConfig.BASE_URL)
            .timeout(15)
            .mutual(false)
            .type(BCPNetworkingType.COROUTINES)
            .build()


        MutualCredentials.pwd = "i4MTh3Pwd!".toCharArray()
        MutualCredentials.certificate = CertificateUtil.certificate(this)

        BCPNetworkingManager.init(bcpNetworkingConfiguration)

*/

        val networkingConfiguration = NetworkingBaseConfiguration.BCPNetworkingBaseConfigurationBuilder()
            .baseUrl(BuildConfig.BASE_URL)
            .timeout(1)
            .mutual(false)
            .type(NetworkingType.COROUTINES)
            .build()

        val bcpNetworking = Networking.BCPNetworkingBuilder()
            .bcpNetworkingBaseConfiguration(networkingConfiguration)
            .pwd("h1l2s4p5e7A8B7P4E5V".toCharArray())
            .certificate(InputStreamUtil.inputStreamToString(CertificateUtil.certificate(this)))
            .build()

        NetworkingManager.init(bcpNetworking)

    }

}