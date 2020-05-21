package com.example.arquitectura_clean_mvvm.screen.pokemon

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.arquitectura_clean_mvvm.R
import com.example.arquitectura_clean_mvvm.screen.ScreenState
import com.example.arquitectura_clean_mvvm.screen.pokemon.adapter.ListPokemonAdapter
import com.example.domain.model.PokemonModel
import com.example.helper.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class PokemonActivity : BaseActivity() {


    private val pokemonViewModel:PokemonViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pokemonViewModel.state.observe(::getLifecycle, ::updateUI)
        pokemonViewModel.getPokemon()

    }

    private fun updateUI(screenState: ScreenState<PokemonState>) {

        when (screenState) {

            ScreenState.Loading -> {
                pbLoad.visibility = View.VISIBLE
            }
            is ScreenState.Render -> processRenderState(screenState.renderState)
        }
    }

    private fun processRenderState(renderState: PokemonState) {

        pbLoad.visibility = View.GONE

        when (renderState) {

            is PokemonState.ShowListItems -> {
                Toast.makeText(this, "hola", Toast.LENGTH_SHORT).show()
                setRecyclerViewPokemon(renderState.list)
            }
        }
    }

    private fun setRecyclerViewPokemon(listPokemon: List<PokemonModel>) {
        val adapterPokemon = ListPokemonAdapter(listPokemon)
        rcvpoke.setHasFixedSize(true)
        rcvpoke.adapter = adapterPokemon
        rcvpoke.layoutManager = GridLayoutManager(this, 4)
    }
}
