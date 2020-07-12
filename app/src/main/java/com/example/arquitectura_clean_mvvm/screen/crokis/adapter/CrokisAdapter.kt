package com.example.arquitectura_clean_mvvm.screen.crokis.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.arquitectura_clean_mvvm.R
import com.example.domain.model.crokis.Crokis
import com.example.domain.model.crokis.Pins
import kotlinx.android.synthetic.main.item_coordinates.view.*

class CrokisAdapter(private val listCrokis: List<Crokis>): RecyclerView.Adapter<CrokisAdapter.CrokisViewHolder>() {

    private lateinit var context: Context
    private lateinit var onMaps:OnMaps

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrokisViewHolder {
        context = parent.context
        return CrokisViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_coordinates,parent,false))
    }

    override fun getItemCount(): Int {
        return listCrokis.size
    }

    override fun onBindViewHolder(holder: CrokisViewHolder, position: Int) {
        holder.txtTitle.text = listCrokis[position].MapName
        holder.txtDate.text = listCrokis[position].LastModDate

        if(listCrokis[position].IsEditable!!){
            holder.imgArrow.background = ContextCompat.getDrawable(context, R.drawable.marker_icon)
            holder.imgSelected.background = ContextCompat.getDrawable(context, R.drawable.unpin_icon_b)
        }else{
            holder.imgArrow.background = ContextCompat.getDrawable(context, R.drawable.ic_arrow_rigth)
            holder.imgSelected.background = ContextCompat.getDrawable(context, R.drawable.pin_icon_b)
        }

        holder.itemView.setOnClickListener {
            onMaps.onMapsCoordinates(listCrokis[position])
        }
    }

    class CrokisViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtTitle = itemView.txtTitle
        val txtDate = itemView.txtDate
        val imgArrow = itemView.imgArrow
        val imgSelected = itemView.imgSelected
    }

    fun setListener(onMaps:OnMaps){
        this.onMaps = onMaps
    }

    interface OnMaps{
        fun onMapsCoordinates(coordinates:Crokis)
    }

}