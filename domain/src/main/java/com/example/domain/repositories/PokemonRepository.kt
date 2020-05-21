package com.example.domain.repositories

import com.example.domain.model.PokemonModel
import com.example.helper.base.ResultType
import com.example.helper.error.Failure

interface PokemonRepository {

    suspend fun getPokemon():ResultType<Failure, List<PokemonModel>>

}