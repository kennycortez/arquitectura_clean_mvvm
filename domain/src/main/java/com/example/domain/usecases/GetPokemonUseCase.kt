package com.example.domain.usecases

import com.example.domain.model.PokemonModel
import com.example.domain.repositories.PokemonRepository
import com.example.helper.base.ResultType
import com.example.helper.base.UseCase
import com.example.helper.error.Failure
import org.koin.core.KoinComponent
import org.koin.core.inject

class GetPokemonUseCase:UseCase<List<PokemonModel>,Unit>(),KoinComponent {

    private val pokemonRepository:PokemonRepository by inject()

    override suspend fun run(params: Unit): ResultType<Failure, List<PokemonModel>> {
        return pokemonRepository.getPokemon()
    }
}