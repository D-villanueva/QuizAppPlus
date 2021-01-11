package com.fiuady.quizappplus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Puntuaciones : AppCompatActivity(){

    private lateinit var recyclerView:RecyclerView
    private lateinit var viewAdapter:RecyclerView.Adapter<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_puntuaciones)

        val puntosusers=arrayOf(
           globales("usuario 1",100),
           globales("usuario 2",100),
           globales("usuario 3",100),
           globales("usuario 4",100),
           globales("usuario 5",100),
           globales("usuario 6",100),
           globales("usuario 7",100),
           globales("usuario 8",100),
           globales("usuario 9",100),
           globales("usuario 10",500),
           globales("usuario 11",100),
           globales("usuario 12",500),

       )
        viewAdapter=globalesAdapter(puntosusers)
        recyclerView=findViewById<RecyclerView>(R.id.puntuaciones ).apply {
        setHasFixedSize(true)

            layoutManager = LinearLayoutManager(this@Puntuaciones)
            adapter=viewAdapter
        }
    }
    //override fun OnUserClick(position:Int){
      //  val detailUser = Intent(this@Puntuaciones, DetailUser::class.java)
       // startActivity(detailUser)
    //}

}