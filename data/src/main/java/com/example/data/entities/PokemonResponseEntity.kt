package com.example.data.entities


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PokemonResponseEntity(
    @SerializedName("pokemon") var pokemon: List<PokemonEntity>
): Serializable