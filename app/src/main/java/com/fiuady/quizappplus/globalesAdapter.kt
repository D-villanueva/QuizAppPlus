package com.fiuady.quizappplus

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class globalesAdapter ( private val puntosusers : Array<globales>) :
    RecyclerView.Adapter<globalesAdapter.globalesHolder>() {

    class globalesHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): globalesHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.puntuaciones_globales, parent, false)
        return globalesHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: globalesHolder, position: Int) {
        //llenar el holder con la informacion que debe llevar
        holder.view.findViewById<TextView>(R.id.user).text=puntosusers[position].User

        holder.view.findViewById<TextView>(R.id.puntos_globales).text= puntosusers[position].Puntos.toString()
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = puntosusers.size
}
