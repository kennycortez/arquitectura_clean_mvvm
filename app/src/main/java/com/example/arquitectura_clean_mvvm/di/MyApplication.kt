package com.example.arquitectura_clean_mvvm.di

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

@HiltAndroidApp
class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        // Start Koin
       /* startKoin(){
            //androidLogger()
            //androidContext(this@MyApplication)
            modules(appModule)
        }
*/
    }

}