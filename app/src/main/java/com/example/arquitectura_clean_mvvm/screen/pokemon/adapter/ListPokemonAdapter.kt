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
            txtId.text = "#${listPokemon.number}"
            txtWeight.text = "${listPokemon.weight ?: 0} kg"
            txtHeight.text = "${listPokemon.height ?: 0} m"
            Picasso.get().load(listPokemon.ThumbnailImage).into(imvpokerastro)

            // Dynamic types
            typesContainer.removeAllViews()
            listPokemon.type?.forEach { typeName ->
                val typeView = LayoutInflater.from(itemView.context).inflate(R.layout.layout_type_badge, typesContainer, false) as android.widget.TextView
                typeView.text = typeName
                
                // Get color based on type
                val colorId = itemView.context.resources.getIdentifier("type_${typeName.lowercase()}", "color", itemView.context.packageName)
                if (colorId != 0) {
                    val color = androidx.core.content.ContextCompat.getColor(itemView.context, colorId)
                    typeView.backgroundTintList = android.content.res.ColorStateList.valueOf(color)
                    // If the background is very light (like electric), we might want darker text, but white is usually fine for these pastels
                    typeView.setTextColor(android.graphics.Color.WHITE)
                }
                
                typesContainer.addView(typeView)
            }

            imvpokerastro.setOnClickListener {
                animateClick(itemView) // Animate the whole card
                audioPoke(listPokemon)
                nClickSelectedPokemon.selectPokemon(listPokemon)
            }
        }

        private fun animateClick(view: View) {
            view.animate()
                .scaleX(0.95f) // Scale down slightly on click for "press" effect
                .scaleY(0.95f)
                .setDuration(100)
                .withEndAction {
                    view.animate()
                        .scaleX(1.0f)
                        .scaleY(1.0f)
                        .setDuration(150)
                        .setInterpolator(android.view.animation.OvershootInterpolator())
                        .start()
                }
                .start()
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