package com.example.arquitectura_clean_mvvm.screen.pokemon

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.arquitectura_clean_mvvm.databinding.ActivityPokemonBinding
import com.example.arquitectura_clean_mvvm.screen.ScreenState
import com.example.arquitectura_clean_mvvm.screen.pokemon.adapter.ListPokemonAdapter
import com.example.domain.model.pokedex.PokedexItemModel
import com.example.helper.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonActivity : BaseActivity(), ListPokemonAdapter.OnClickSelectedPokemon {

    private lateinit var binding: ActivityPokemonBinding

    //private val pokemonViewModel:PokemonViewModel by viewModel()
    private val pokemonViewModel:PokemonViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPokemonBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_pokemon)
        pokemonViewModel.state.observe(::getLifecycle, ::updateUI)
        pokemonViewModel.getPokemon()

    }

    private fun updateUI(screenState: ScreenState<PokemonState>) {

        when (screenState) {

            ScreenState.Loading -> {
                binding.pbLoad.visibility = View.VISIBLE
            }
            is ScreenState.Render -> processRenderState(screenState.renderState)
        }
    }

    private fun processRenderState(renderState: PokemonState) {

        binding.pbLoad.visibility = View.GONE

        when (renderState) {

            is PokemonState.ShowListItems -> {
                Toast.makeText(this, "hola", Toast.LENGTH_SHORT).show()
                setRecyclerViewPokemon(renderState.list)
            }
        }
    }

    private fun setRecyclerViewPokemon(listPokemon: List<PokedexItemModel>) {
        val adapterPokemon = ListPokemonAdapter(listPokemon)
        binding.rcvpoke.setHasFixedSize(true)
        binding.rcvpoke.adapter = adapterPokemon
        binding.rcvpoke.layoutManager = GridLayoutManager(this, 4)
        adapterPokemon.setListenerItemSelected(this)
    }

    override fun selectPokemon(pokemon: PokedexItemModel) {
        print(pokemon)
    }

}
