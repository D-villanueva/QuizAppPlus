package com.fiuady.quizappplus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class final_score : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final_score)
        val puntosusers=arrayOf(
            final("hola",1,2),

            )


        viewAdapter=FinalScoreAdapter(puntosusers)
        recyclerView=findViewById<RecyclerView>(R.id.altas ).apply {
            setHasFixedSize(true)

            layoutManager = LinearLayoutManager(this@final_score)
            adapter=viewAdapter

        }

    }
}