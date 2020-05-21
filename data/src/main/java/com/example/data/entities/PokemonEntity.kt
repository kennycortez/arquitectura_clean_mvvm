package com.example.data.entities


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PokemonEntity(
    @SerializedName("avg_spawns") var avgSpawns: Double?,
    @SerializedName("candy") var candy: String?,
    @SerializedName("candy_count") var candyCount: Int?,
    @SerializedName("egg") var egg: String?,
    @SerializedName("height") var height: String?,
    @SerializedName("id") var id: Int?,
    @SerializedName("img") var img: String?,
    @SerializedName("multipliers") var multipliers: List<Double>?= arrayListOf(),
    @SerializedName("name") var name: String?,
    @SerializedName("next_evolution") var nextEvolution: List<NextEvolutionEntity>?= arrayListOf(),
    @SerializedName("num") var num: String?,
    @SerializedName("prev_evolution") var prevEvolution: List<PrevEvolutionEntity>?= arrayListOf(),
    @SerializedName("spawn_chance") var spawnChance: Double?,
    @SerializedName("spawn_time") var spawnTime: String?,
    @SerializedName("type") var type: List<String>?= arrayListOf(),
    @SerializedName("weaknesses") var weaknesses: List<String>?= arrayListOf(),
    @SerializedName("weight") var weight: String?
): Serializable