package com.fiuady.quizappplus

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.final_score.*

class final_score : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var textuserplaying:TextView
    private lateinit var pistas:TextView
    private lateinit var puntos:TextView
    private lateinit var imageprincipal:ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final_score)
        val dbBuilder: DbBuilder by viewModels()
        val db = dbBuilder.buildBd(this)

        pistas=findViewById(R.id.cheats_number)
        puntos=findViewById(R.id.puntos)
        textuserplaying=findViewById(R.id.user)
        imageprincipal=findViewById(R.id.image_score)

        var usuario_activo=db.usersDao().getActiveUser()
        var results=db.scoresDao().getresults(usuario_activo.id)
        textuserplaying.text=results.user_name
        puntos.text=results.ppartida.toString()
        pistas.text=results.cheats.toString()

        if(results.dificultad==1){
            imageprincipal.setImageResource(R.drawable.medal)

        }else if(results.dificultad==2){
            imageprincipal.setImageResource(R.drawable.price)
        }else{
            imageprincipal.setImageResource(R.drawable.firstplace)
        }

        val altas=db.scoresDao().getpuntosaltos()

        viewAdapter=FinalScoreAdapter(altas)
        recyclerView=findViewById<RecyclerView>(R.id.altas).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@final_score)
            adapter=viewAdapter
        }

    }
}