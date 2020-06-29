package com.example.data.mapper.pokemon.pokedex

import com.example.data.entities.pokedex.PokedexItemEntity
import com.example.data.mapper.Mapper
import com.example.domain.model.pokedex.PokedexItemModel
import com.example.domain.model.pokedex.PokedexModel

class PokedexMapper : Mapper<PokedexItemModel,PokedexItemEntity>(){


    override fun mapToEntity(type: PokedexItemEntity): PokedexItemModel {
        TODO("Not yet implemented")
    }

    override fun mapToEntity(type: List<PokedexItemEntity>): List<PokedexItemModel> {

        var listPokedex = mutableListOf<PokedexItemModel>()

        type.forEach {
            var poke = PokedexItemModel(it.thumbnailAltText,it.thumbnailImage,it.abilities,it.collectiblesSlug,it.detailPageURL,it.featured,
            it.height,it.id,it.name,it.number,it.collectiblesSlug,it.type,it.weakness,it.weight)
            listPokedex.add(poke)
        }

        return listPokedex
    }
}