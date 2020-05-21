package com.example.data.implements

import android.content.Context
import com.example.data.entities.exception.ErrorEntity
import com.example.data.entities.PokemonResponseEntity
import com.example.data.mapper.error.ErrorMapper
import com.example.data.mapper.pokemon.PokemonMapper
import com.example.data.network.ErrorUtil
import com.example.data.network.Network
import com.example.domain.model.PokemonModel
import com.example.domain.repositories.PokemonRepository
import com.example.helper.base.ResultType
import com.example.helper.error.Failure
import com.example.networking.NetworkingManager
import com.example.networking.model.NetworkingConfiguration
import com.example.networking.service.ResultService
import com.example.networking.util.NetworkingHttpVerb
import com.google.gson.Gson
import java.lang.Exception

class PokemonRepositoryImpl(context: Context):PokemonRepository {

    private val gson = Gson()

    override suspend fun getPokemon(): ResultType<Failure, List<PokemonModel>> {

        val query = "pokemon"
        val networkConfiguration = NetworkingConfiguration.CPNetworkingConfigurationBuilder()
            .endpoint(query)
            .header(Network.getGenericHeader())
            .httpVerb(NetworkingHttpVerb.GET)
            .config()

        return try {
            val result: ResultService= NetworkingManager.with(networkConfiguration).connect()

            return if(result.isSuccessful){

                val pokemonResponse: PokemonResponseEntity = gson.fromJson(gson.toJson(result.body), PokemonResponseEntity::class.java)
                ResultType.Success(PokemonMapper().mapToEntity(pokemonResponse.pokemon))

            }else{
                val error = gson.fromJson(gson.toJson(result.error), ErrorEntity::class.java)

                ResultType.Failure(Failure.ServerError(ErrorMapper().mapToEntity(error)))
            }
        }catch (exception:Exception){
            ResultType.Failure(ErrorUtil.handler(exception))
        }
    }

}