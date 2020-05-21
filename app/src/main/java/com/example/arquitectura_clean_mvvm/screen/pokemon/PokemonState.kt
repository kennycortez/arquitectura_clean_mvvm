package com.example.arquitectura_clean_mvvm.screen.pokemon

import com.example.domain.model.PokemonModel

sealed class PokemonState {
    class ShowListItems(val list: List<PokemonModel>) : PokemonState()
}