package com.example.data.mapper.pokemon.pokedex

import com.example.data.entities.pokedex.PokedexItemEntity
import com.example.data.mapper.Mapper
import com.example.domain.model.pokedex.PokedexItemModel

class PokedexMapper : Mapper<PokedexItemModel,PokedexItemEntity>(){


    override fun mapToEntity(type: PokedexItemEntity): PokedexItemModel {
        // We use a more reliable image source from PokeAPI GitHub to avoid blocking
        val officialArtwork = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${type.id}.png"
        
        return PokedexItemModel(
            ThumbnailAltText = type.thumbnailAltText,
            ThumbnailImage = officialArtwork,
            abilities = type.abilities,
            collectibles_slug = type.collectiblesSlug,
            detailPageURL = type.detailPageURL,
            featured = type.featured,
            height = type.height,
            id = type.id,
            name = type.name,
            number = type.number,
            slug = type.slug,
            type = type.type,
            weakness = type.weakness,
            weight = type.weight
        )
    }

    override fun mapToEntity(type: List<PokedexItemEntity>): List<PokedexItemModel> {
        return type.map { mapToEntity(it) }
    }
}
