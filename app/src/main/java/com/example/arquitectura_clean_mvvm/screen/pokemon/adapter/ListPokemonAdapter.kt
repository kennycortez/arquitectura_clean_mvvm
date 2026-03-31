package com.example.arquitectura_clean_mvvm.screen.pokemon.adapter

import android.media.MediaPlayer
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.arquitectura_clean_mvvm.R
import com.example.arquitectura_clean_mvvm.databinding.ItemPokeBinding
import com.example.domain.model.pokedex.PokedexItemModel
import com.squareup.picasso.Picasso
import java.util.Locale

class ListPokemonAdapter(private val listPokemon: List<PokedexItemModel>) :
    RecyclerView.Adapter<ListPokemonAdapter.ListPokemonViewHolder>() {

    var mOnClickSelectedPokemon: OnClickSelectedPokemon? = null
    private var textToSpeech: TextToSpeech? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListPokemonViewHolder {
        if (textToSpeech == null) {
            textToSpeech = TextToSpeech(parent.context) { status ->
                if (status == TextToSpeech.SUCCESS) {
                    textToSpeech?.language = Locale("es", "ES")
                }
            }
        }
        return ListPokemonViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_poke, parent, false),
            textToSpeech
        )
    }

    override fun getItemCount(): Int {
        return listPokemon.size
    }

    override fun onBindViewHolder(holder: ListPokemonViewHolder, position: Int) {
        holder.bind(listPokemon[position], mOnClickSelectedPokemon!!)
    }

    class ListPokemonViewHolder(itemView: View, private val tts: TextToSpeech?) : RecyclerView.ViewHolder(itemView){

        private var binding = ItemPokeBinding.bind(itemView)

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
                
                val colorId = itemView.context.resources.getIdentifier("type_${typeName.lowercase()}", "color", itemView.context.packageName)
                if (colorId != 0) {
                    val color = androidx.core.content.ContextCompat.getColor(itemView.context, colorId)
                    typeView.backgroundTintList = android.content.res.ColorStateList.valueOf(color)
                    typeView.setTextColor(android.graphics.Color.WHITE)
                }
                
                typesContainer.addView(typeView)
            }

            imvpokerastro.setOnClickListener {
                animateClick(itemView) 
                speakPokemonName(listPokemon.name) {
                    audioPoke(listPokemon)
                }
                nClickSelectedPokemon.selectPokemon(listPokemon)
            }
        }

        private fun speakPokemonName(name: String?, onDone: () -> Unit) {
            if (tts == null || name == null) {
                onDone()
                return
            }

            val utteranceId = "poke_name_${name}"
            tts.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
                override fun onStart(utteranceId: String?) {}
                override fun onDone(uid: String?) {
                    if (uid == utteranceId) {
                        itemView.post { onDone() }
                    }
                }
                override fun onError(utteranceId: String?) {
                    itemView.post { onDone() }
                }
            })

            tts.speak(name, TextToSpeech.QUEUE_FLUSH, null, utteranceId)
        }

        private fun animateClick(view: View) {
            view.animate()
                .scaleX(0.95f)
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
                if (resID != 0) {
                    val mediaPlayer = MediaPlayer.create(itemView.context, resID)
                    mediaPlayer.start()
                    mediaPlayer.setOnCompletionListener { mp ->
                        mp.release()
                    }
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
