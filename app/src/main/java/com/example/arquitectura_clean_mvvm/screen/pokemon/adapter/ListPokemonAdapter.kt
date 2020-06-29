package com.example.arquitectura_clean_mvvm.screen.pokemon.adapter

import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.arquitectura_clean_mvvm.R
import com.example.arquitectura_clean_mvvm.databinding.ItemPokeBinding
import com.example.domain.model.PokemonModel
import com.example.domain.model.pokedex.PokedexItemModel
import com.squareup.picasso.Picasso


class ListPokemonAdapter(private val listPokemon: List<PokedexItemModel>) :
    RecyclerView.Adapter<ListPokemonAdapter.ListPokemonViewHolder>() {

    var mOnClickSelectedPokemon: OnClickSelectedPokemon? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListPokemonViewHolder {
        return ListPokemonViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_poke, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return listPokemon.size
    }

    override fun onBindViewHolder(holder: ListPokemonViewHolder, position: Int) {

        holder.bind(listPokemon[position], mOnClickSelectedPokemon!!)


        /*holder.binding.txtname.text = listPokemon[position].name
        Picasso.get().load(listPokemon[position].img).into(holder.binding.imvpokerastro)
        holder.binding.imvpokerastro.setOnClickListener {

            try {
                val nameAudio: String? = listPokemon[position].num
                val resID = context!!.resources.getIdentifier("p$nameAudio", "raw", context!!.packageName)
                val mediaPlayer = MediaPlayer.create(context, resID)
                mediaPlayer.start()
                mediaPlayer.setOnCompletionListener { mp ->
                    mp.release()
                }

            } catch (e: Exception) {
                e.message
                e.printStackTrace()
            }
        }*/

    }

    class ListPokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private var binding = ItemPokeBinding.bind(itemView)/*.apply {
                this.txtname
                this.imvpokerastro
        }*/



        fun bind(
            listPokemon: PokedexItemModel,
            nClickSelectedPokemon: OnClickSelectedPokemon
        ) = with(binding) {

            txtname.text = listPokemon.name
            Picasso.get().load(listPokemon.ThumbnailImage).into(imvpokerastro)

            imvpokerastro.setOnClickListener {
                audioPoke(listPokemon)
                nClickSelectedPokemon.selectPokemon(listPokemon)
            }
        }

        private fun audioPoke(listPokemon: PokedexItemModel) {
            try {
                val nameAudio: String? = listPokemon.number
                val resID = itemView.context.resources.getIdentifier(
                    "p$nameAudio",
                    "raw",
                    itemView.context.packageName
                )
                val mediaPlayer = MediaPlayer.create(itemView.context, resID)
                mediaPlayer.start()
                mediaPlayer.setOnCompletionListener { mp ->
                    mp.release()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

    fun setListenerItemSelected(setOnClickSelectedPokemon: OnClickSelectedPokemon) {
        mOnClickSelectedPokemon = setOnClickSelectedPokemon
    }

    interface OnClickSelectedPokemon {
        fun selectPokemon(pokemon: PokedexItemModel)
    }
}