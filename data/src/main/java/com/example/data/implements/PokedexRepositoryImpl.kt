package com.example.data.implements

import com.example.data.entities.PokemonResponseEntity
import com.example.data.entities.exception.ErrorEntity
import com.example.data.entities.pokedex.PokedexEntity
import com.example.data.entities.pokedex.PokedexItemEntity
import com.example.data.mapper.error.ErrorMapper
import com.example.data.mapper.pokemon.PokemonMapper
import com.example.data.mapper.pokemon.pokedex.PokedexMapper
import com.example.data.network.ErrorUtil
import com.example.data.network.Network
import com.example.domain.model.pokedex.PokedexItemModel
import com.example.domain.repositories.PokedexRepository
import com.example.helper.base.ResultType
import com.example.helper.error.Failure
import com.example.networking.NetworkingManager
import com.example.networking.model.NetworkingConfiguration
import com.example.networking.service.ResultService
import com.example.networking.util.NetworkingHttpVerb
import com.google.gson.Gson
import java.lang.Exception
import javax.inject.Inject

class PokedexRepositoryImpl @Inject constructor():PokedexRepository {

    private val gson = Gson()

    override suspend fun getPokedex(): ResultType<Failure, List<PokedexItemModel>> {
        val query = "pokedex"
        val networkConfiguration = NetworkingConfiguration.CPNetworkingConfigurationBuilder()
            .endpoint(query)
            .header(Network.getGenericHeader())
            .httpVerb(NetworkingHttpVerb.GET)
            .config()

        return try {
            val result: ResultService = NetworkingManager.with(networkConfiguration).connect()

            return if(result.isSuccessful){
                val pokemonResponse: PokedexEntity = gson.fromJson(gson.toJson(result.body), PokedexEntity::class.java)
                ResultType.Success(PokedexMapper().mapToEntity(pokemonResponse))

            }else{
                val error = gson.fromJson(gson.toJson(result.error), ErrorEntity::class.java)

                ResultType.Failure(Failure.ServerError(ErrorMapper().mapToEntity(error)))
            }
        }catch (exception: Exception){
            ResultType.Failure(ErrorUtil.handler(exception))
        }
    }

}