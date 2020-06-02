package com.example.arquitectura_clean_mvvm.di.dagger

import android.app.Application
import com.example.arquitectura_clean_mvvm.di.MyApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector

@Component
interface AppComponent: AndroidInjector<MyApplication>{

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
}