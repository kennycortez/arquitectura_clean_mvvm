package com.example.domain.repositories

import com.example.domain.model.pokedex.PokedexItemModel
import com.example.helper.base.ResultType
import com.example.helper.error.Failure

interface PokedexRepository {

    suspend fun getPokedex():ResultType<Failure,List<PokedexItemModel>>

}