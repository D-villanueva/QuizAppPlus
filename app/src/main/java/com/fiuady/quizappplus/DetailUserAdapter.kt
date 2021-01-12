package com.fiuady.quizappplus

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DetailUserAdapter (private val detailUser : Array<details>) : RecyclerView.Adapter<DetailUserAdapter.detailsHolder>() {

    class detailsHolder(val view: View) : RecyclerView.ViewHolder(view)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): detailsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.detail_user, parent, false)
        return detailsHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: detailsHolder, position: Int) {

        holder.view.findViewById<TextView>(R.id.fecha).text=detailUser[position].fecha
        holder.view.findViewById<TextView>(R.id.pistas_number).text= detailUser[position].Pistas.toString()
        holder.view.findViewById<TextView>(R.id.level).text= detailUser[position].level
        holder.view.findViewById<TextView>(R.id.puntos).text= detailUser[position].Puntos.toString()

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = detailUser.size

}