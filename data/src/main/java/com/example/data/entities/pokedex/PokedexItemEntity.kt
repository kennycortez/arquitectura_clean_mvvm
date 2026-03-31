package com.example.data.entities.pokedex

import com.google.gson.annotations.SerializedName

data class PokedexItemEntity(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("num")
    val number: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("img")
    val thumbnailImage: String?,
    @SerializedName("type")
    val type: List<String>?,
    @SerializedName("height")
    val heightStr: String?,
    @SerializedName("weight")
    val weightStr: String?,
    @SerializedName("candy")
    val candy: String?,
    @SerializedName("egg")
    val egg: String?,
    @SerializedName("spawn_chance")
    val spawnChance: Double?,
    @SerializedName("avg_spawns")
    val avgSpawns: Double?,
    @SerializedName("spawn_time")
    val spawnTime: String?,
    @SerializedName("multipliers")
    val multipliers: List<Double>?,
    @SerializedName("weaknesses")
    val weakness: List<String>?
)
