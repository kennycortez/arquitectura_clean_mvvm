package com.example.arquitectura_clean_mvvm.di.dagger.hilt

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.data.datasource.PokedexDataStoreFactory
import com.example.data.implements.PokedexRepositoryImpl
import com.example.data.implements.PokemonRepositoryImpl
import com.example.data.local.AppDataBase
import com.example.domain.repositories.PokedexRepository
import com.example.domain.repositories.PokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class AppModule {

    @Provides
    fun dataBasePokedex(context: Context):AppDataBase{
        return Room.databaseBuilder(
            context,
            AppDataBase::class.java, "pokedex.db"
        ).build()
    }


    @Provides
    fun provideContext(application: Application): Context{
        return application
    }

    @Singleton
    @Provides
    fun pokemonRepository():PokemonRepository{
        return PokemonRepositoryImpl()
    }

    @Singleton
    @Provides
    fun pokedexRepository(pokedexDataStoreFactory: PokedexDataStoreFactory,appDataBase: AppDataBase):PokedexRepository{
        return PokedexRepositoryImpl(pokedexDataStoreFactory,appDataBase)
    }


}