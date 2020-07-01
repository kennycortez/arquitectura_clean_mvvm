package com.example.data.datasource

import com.example.data.datasource.local.PokemonLocal
import com.example.data.datasource.remote.PokedexRemote
import com.example.data.local.AppDataBase
import com.example.data.network.ErrorUtil
import com.example.domain.model.pokedex.PokedexItemModel
import com.example.helper.base.ResultType
import com.example.helper.error.Failure
import javax.inject.Inject

class PokedexDataStoreFactory @Inject constructor(private val pokedexRemote: PokedexRemote, private val pokedexLocal: PokemonLocal) {

    suspend fun retrieveRemoteDataStore(query:String): ResultType<Failure, List<PokedexItemModel>> {
        return pokedexRemote.getPokedexCompletable(query)
    }

    suspend fun retrieveLocalDataStore(): ResultType<Failure, List<PokedexItemModel>>{
        return  ResultType.Failure(Failure.DefaultError)
    }
        //return pokedexLocal


}