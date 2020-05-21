package com.example.domain.model

data class PokemonModel(
    var avg_spawns: Double?,
    var candy: String?,
    var candy_count: Int?,
    var egg: String?,
    var height: String?,
    var id: Int?,
    var img: String?,
    var multipliers: List<Double?>?,
    var name: String?,
    var next_evolution: List<NextEvolutionModel>?= arrayListOf(),
    var num: String?,
    var prev_evolution: List<PrevEvolutionModel>?= arrayListOf(),
    var spawn_chance: Double?,
    var spawn_time: String?,
    var type: List<String>?= arrayListOf(),
    var weaknesses: List<String>?= arrayListOf(),
    var weight: String?
)