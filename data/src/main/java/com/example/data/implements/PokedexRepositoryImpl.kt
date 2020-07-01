package com.example.data.implements

import com.example.data.datasource.PokedexDataStoreFactory
import com.example.data.datasource.remote.PokedexRemote
import com.example.data.entities.exception.ErrorEntity
import com.example.data.entities.pokedex.PokedexEntity
import com.example.data.local.AppDataBase
import com.example.data.local.Pokedex
import com.example.data.local.PokedexDao
import com.example.data.mapper.error.ErrorMapper
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
import kotlinx.coroutines.*
import java.lang.Exception
import javax.inject.Inject

class PokedexRepositoryImpl @Inject constructor(private val pokedexDataStoreFactory: PokedexDataStoreFactory,private val appDataBase: AppDataBase) :
    PokedexRepository {

    override suspend fun getPokedex(): ResultType<Failure, List<PokedexItemModel>> {

        return if(true/*x().isNotEmpty()*/){
             pokedexDataStoreFactory.retrieveRemoteDataStore("pokedex")
        }else{
            pokedexDataStoreFactory.retrieveLocalDataStore()
        }

    }

    fun x(): List<Pokedex>{
        var list:List<Pokedex> = arrayListOf()
        CoroutineScope(Dispatchers.Default ) .launch {
            try {
                list =appDataBase.pokedexDao().getAll()
            } catch (e: Exception ) {
                this.cancel()
            }
        }
        return list
    }

}