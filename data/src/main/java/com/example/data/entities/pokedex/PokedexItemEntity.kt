package com.example.data.entities.pokedex


import com.google.gson.annotations.SerializedName

data class PokedexItemEntity(
    @SerializedName("abilities")
    val abilities: List<String>?,
    @SerializedName("collectibles_slug")
    val collectiblesSlug: String?,
    @SerializedName("detailPageURL")
    val detailPageURL: String?,
    @SerializedName("featured")
    val featured: String?,
    @SerializedName("height")
    val height: Double?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("number")
    val number: String?,
    @SerializedName("slug")
    val slug: String?,
    @SerializedName("ThumbnailAltText")
    val thumbnailAltText: String?,
    @SerializedName("ThumbnailImage")
    val thumbnailImage: String?,
    @SerializedName("type")
    val type: List<String>?,
    @SerializedName("weakness")
    val weakness: List<String>?,
    @SerializedName("weight")
    val weight: Double?
)