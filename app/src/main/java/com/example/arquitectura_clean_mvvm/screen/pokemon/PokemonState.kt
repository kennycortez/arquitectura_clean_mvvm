package com.example.arquitectura_clean_mvvm.screen.pokemon

import com.example.domain.model.PokemonModel
import com.example.domain.model.pokedex.PokedexItemModel

sealed class PokemonState {
    class ShowListItems(val list: List<PokedexItemModel>) : PokemonState()
}