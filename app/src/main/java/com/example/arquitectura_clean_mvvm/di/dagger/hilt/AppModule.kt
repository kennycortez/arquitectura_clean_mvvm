package com.example.arquitectura_clean_mvvm.di.dagger.hilt

import com.example.data.implements.PokedexRepositoryImpl
import com.example.data.implements.PokemonRepositoryImpl
import com.example.domain.repositories.PokedexRepository
import com.example.domain.repositories.PokemonRepository
import com.example.storage.Storage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun pokemonRepository():PokemonRepository{
        return PokemonRepositoryImpl()
    }

    @Singleton
    @Provides
    fun pokedexRepository():PokedexRepository{
        return PokedexRepositoryImpl()
    }
}