package com.example.arquitectura_clean_mvvm.screen.pokemon

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.arquitectura_clean_mvvm.screen.ScreenState
import com.example.domain.model.PokemonModel
import com.example.domain.usecases.GetPokemonUseCase
import com.example.helper.error.Failure
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent

class PokemonViewModel @ViewModelInject constructor(private val getPokemonUseCase:GetPokemonUseCase):ViewModel(),KoinComponent {

    //private val getPokemonUseCase:GetPokemonUseCase by inject()

    private var _state: MutableLiveData<ScreenState<PokemonState>> = MutableLiveData()

    val state: LiveData<ScreenState<PokemonState>>
        get() = _state


    fun getPokemon( ) {
        _state.value = ScreenState.Loading
        viewModelScope.launch { getPokemonUseCase.run(Unit).either(::error, ::responsePokemon) }
    }

    private fun responsePokemon(account: List<PokemonModel>) {

        when{
            account.isEmpty() ->  _state.value = ScreenState.Render(PokemonState.ShowListItems(account))

            else -> _state.value = ScreenState.Render(PokemonState.ShowListItems(account))
        }

    }

    private fun error(fail: Failure) {
        println(fail)
       // _error.value = Event(fail)
    }

}