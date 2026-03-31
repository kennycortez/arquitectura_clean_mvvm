package com.example.data.entities.pokedex

import com.google.gson.annotations.SerializedName

data class PokedexEntity(
    @SerializedName("pokemon")
    val pokemon: List<PokedexItemEntity>
)