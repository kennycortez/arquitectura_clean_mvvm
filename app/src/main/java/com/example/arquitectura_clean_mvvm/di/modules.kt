package com.example.arquitectura_clean_mvvm.di

import com.example.arquitectura_clean_mvvm.screen.pokemon.PokemonViewModel
import com.example.data.implements.PokemonRepositoryImpl
import com.example.domain.repositories.PokemonRepository
import com.example.domain.usecases.GetPokemonUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel

import org.koin.dsl.module


val appModule = module {

   /* viewModel { PokemonViewModel () }

    single{ GetPokemonUseCase() }

    //Cualquier cosa que necisites el contexto , asi lo puedes invocar...
    single<PokemonRepository> { PokemonRepositoryImpl()}
*/ 
}
