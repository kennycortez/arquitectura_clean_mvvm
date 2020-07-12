package com.example.arquitectura_clean_mvvm.di.dagger.hilt

import android.app.Application
import android.content.Context
import com.example.arquitectura_clean_mvvm.firebase.FirebaseDataHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
class AppModule {

    @Provides
    fun provideContext(application: Application): Context{
        return application
    }

   // @Singleton
    @Provides
    fun firebaseDataHelperUser():FirebaseDataHelper{
        return FirebaseDataHelper()
    }

}