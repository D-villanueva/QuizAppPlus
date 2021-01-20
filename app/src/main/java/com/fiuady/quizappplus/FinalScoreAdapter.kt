package com.fiuady.quizappplus

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fiuady.quizappplus.db.Scores

class FinalScoreAdapter  (private val detailpuntos : Array<Scores>) : RecyclerView.Adapter<FinalScoreAdapter.finalHolder>() {

    class finalHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): finalHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.final_score, parent, false)
        return finalHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: finalHolder, position: Int) {

        holder.view.findViewById<TextView>(R.id.user).text=detailpuntos[position].user_name
        holder.view.findViewById<TextView>(R.id.pistas).text= detailpuntos[position].cheats.toString()
        holder.view.findViewById<TextView>(R.id.puntos_globales).text= detailpuntos[position].ppartida.toString()

    }
    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = detailpuntos.size

}