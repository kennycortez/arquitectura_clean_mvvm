package com.example.data.network

import android.content.Context
import android.util.Log

object Network {

    fun getGenericHeader(): HashMap<String, String>{

        val genericHeader = HashMap<String, String>()
        //genericHeader["Authorization"] = "Bearer ${coreStorage.getString("token", StorageLevel.NON_SENSITIVE_NON_PERSIST) ?: ""}"

        genericHeader["access_token"] = "7ff63604-9e61-4c5b-ad69-48c06fab041e"
        //genericHeader["accept"] = "application/json"
        //genericHeader["X-API-KeyEntity"] = "a2e41630"
//        genericHeader["Request-ID"] = "550e8400-e29b-41d4-a716-446655440000"
//        genericHeader["caller-name"] = "channel-mbob-landing-v1"
//        genericHeader["app-code"] = "MK"

        Log.d("genericHeader",genericHeader.toString())

        return genericHeader

    }

}