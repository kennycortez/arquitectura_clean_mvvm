package com.example.arquitectura_clean_mvvm.screen.pokemon.adapter

import android.content.Context
import android.graphics.BitmapFactory
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.arquitectura_clean_mvvm.R
import com.example.domain.model.PokemonModel
import com.squareup.picasso.Picasso
import java.net.URL


class ListPokemonAdapter(private val listPokemon: List<PokemonModel>) : RecyclerView.Adapter<ListPokemonAdapter.ListPokemonViewHolder>() {

    private var context : Context ?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListPokemonViewHolder {
        context = parent.context
        return ListPokemonViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_poke,parent,false))
    }

    override fun getItemCount(): Int {
        return listPokemon.size
    }

    override fun onBindViewHolder(holder: ListPokemonViewHolder, position: Int) {

        holder.txtNamePokemon.text = listPokemon[position].name
        Picasso.get().load(listPokemon[position].img).into(holder.imagePokemon)
        //Glide.with(context!!).load(listPokemon.get(position).img).into(holder.imagePokemon)

        holder.imagePokemon.setOnClickListener {

            try {
                val nomPoke: String? = listPokemon[position].name
                val nameAudio: String? = listPokemon[position].num
                val imgPoke: String? = listPokemon[position].img
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
        }

    }

    class ListPokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var txtNamePokemon:TextView = itemView.findViewById(R.id.txtname)
            var imagePokemon:ImageView = itemView.findViewById(R.id.imvpokerastro)
    }
}