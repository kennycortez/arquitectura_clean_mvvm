package com.example.data.mapper.pokemon.pokedex

import com.example.data.entities.pokedex.PokedexItemEntity
import com.example.data.mapper.Mapper
import com.example.domain.model.pokedex.PokedexItemModel

class PokedexMapper : Mapper<PokedexItemModel,PokedexItemEntity>(){


    override fun mapToEntity(type: PokedexItemEntity): PokedexItemModel {
        return PokedexItemModel(
            ThumbnailAltText = type.name,
            ThumbnailImage = type.thumbnailImage?.replace("http://", "https://"), // Use HTTPS
            abilities = null, // Not in this JSON
            collectibles_slug = type.name?.lowercase(),
            detailPageURL = null,
            featured = null,
            height = type.heightStr?.replace(" m", "")?.toDoubleOrNull(),
            id = type.id,
            name = type.name,
            number = type.number,
            slug = type.name?.lowercase(),
            type = type.type,
            weakness = type.weakness,
            weight = type.weightStr?.replace(" kg", "")?.toDoubleOrNull()
        )
    }

    override fun mapToEntity(type: List<PokedexItemEntity>): List<PokedexItemModel> {
        return type.map { mapToEntity(it) }
    }
}
