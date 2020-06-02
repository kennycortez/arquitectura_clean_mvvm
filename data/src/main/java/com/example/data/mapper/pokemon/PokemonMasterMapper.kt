package com.example.data.mapper.pokemon

import com.example.data.entities.PokemonResponseEntity
import com.example.data.mapper.Mapper
import com.example.domain.model.PokemonMasterModel
import com.example.domain.model.PokemonModel
import javax.inject.Inject

class PokemonMasterMapper:Mapper<PokemonMasterModel,PokemonResponseEntity>() {

    @Inject
    lateinit var pokemonMapper:PokemonMapper

    override fun mapToEntity(type: PokemonResponseEntity): PokemonMasterModel {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun mapToEntity(type: List<PokemonResponseEntity>): List<PokemonMasterModel> {
        val itemList:MutableList<PokemonMasterModel> = mutableListOf()
        type.let {
            val pokemonMasterModel = PokemonMasterModel(pokemonMapper.mapToEntity(it[0].pokemon))
            itemList.add(pokemonMasterModel)
        }
        return itemList
    }

}