package com.example.domain.model.pokedex

data class PokedexItemModel(
    val ThumbnailAltText: String?,
    val ThumbnailImage: String?,
    val abilities: List<String>?,
    val collectibles_slug: String?,
    val detailPageURL: String?,
    val featured: String?,
    val height: Double?,
    val id: Int?,
    val name: String?,
    val number: String?,
    val slug: String?,
    val type: List<String>?,
    val weakness: List<String>?,
    val weight: Double?
)