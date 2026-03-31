package com.example.data.entities.pokedex

import com.google.gson.annotations.SerializedName

data class PokedexItemEntity(
    @SerializedName("abilities")
    val abilities: List<String>?,
    @SerializedName("detailPageURL")
    val detailPageURL: String?,
    @SerializedName("weight")
    val weight: Double?,
    @SerializedName("weakness")
    val weakness: List<String>?,
    @SerializedName("number")
    val number: String?,
    @SerializedName("height")
    val height: Double?,
    @SerializedName("collectibles_slug")
    val collectiblesSlug: String?,
    @SerializedName("featured")
    val featured: String?,
    @SerializedName("slug")
    val slug: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("ThumbnailAltText")
    val thumbnailAltText: String?,
    @SerializedName("ThumbnailImage")
    val thumbnailImage: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("type")
    val type: List<String>?
)
