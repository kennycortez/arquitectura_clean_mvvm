package com.example.arquitectura_clean_mvvm.screen.pokemon

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
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
    private val pokemonViewModel: PokemonViewModel by viewModels()
    
    private var fullPokemonList: List<PokedexItemModel> = emptyList()
    private val generations = listOf(
        "Todos", "Gen 1", "Gen 2", "Gen 3", "Gen 4", "Gen 5", "Gen 6", "Gen 7", "Gen 8", "Gen 9"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPokemonBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "✨ Mis Pokémones ✨"
        
        setupSpinner()
        pokemonViewModel.state.observe(this, ::updateUI)
        pokemonViewModel.getPokemon()
    }

    private fun setupSpinner() {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, generations)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerGeneration.adapter = adapter
        
        binding.spinnerGeneration.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                filterByGeneration(position)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun filterByGeneration(position: Int) {
        if (fullPokemonList.isEmpty()) return
        
        val filteredList = when (position) {
            0 -> fullPokemonList // Todos
            1 -> fullPokemonList.filter { it.id in 1..151 }
            2 -> fullPokemonList.filter { it.id in 152..251 }
            3 -> fullPokemonList.filter { it.id in 252..386 }
            4 -> fullPokemonList.filter { it.id in 387..493 }
            5 -> fullPokemonList.filter { it.id in 494..649 }
            6 -> fullPokemonList.filter { it.id in 650..721 }
            7 -> fullPokemonList.filter { it.id in 722..809 }
            8 -> fullPokemonList.filter { it.id in 810..905 }
            9 -> fullPokemonList.filter { it.id in 906..1025 }
            else -> fullPokemonList
        }
        
        setRecyclerViewPokemon(filteredList)
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
                fullPokemonList = renderState.list
                filterByGeneration(binding.spinnerGeneration.selectedItemPosition)
            }
        }
    }

    private fun setRecyclerViewPokemon(listPokemon: List<PokedexItemModel>) {
        val adapterPokemon = ListPokemonAdapter(listPokemon)
        binding.rcvpoke.setHasFixedSize(true)
        binding.rcvpoke.adapter = adapterPokemon
        binding.rcvpoke.layoutManager = GridLayoutManager(this, 2)
        adapterPokemon.setListenerItemSelected(this)
    }

    override fun selectPokemon(pokemon: PokedexItemModel) {
        println(pokemon)
    }
}
