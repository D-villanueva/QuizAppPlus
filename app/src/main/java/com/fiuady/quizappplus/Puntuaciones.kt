package com.fiuady.quizappplus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fiuady.quizappplus.db.SumPojo
import kotlinx.android.synthetic.main.detail_user.*
import kotlinx.android.synthetic.main.final_score.*

class  Puntuaciones : AppCompatActivity(),OnUserClickListener{

    private lateinit var recyclerView:RecyclerView
    private lateinit var viewAdapter:RecyclerView.Adapter<*>
    var puntosglobales= emptyArray<SumPojo>()

    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_puntuaciones)
            val dbBuilder: DbBuilder by viewModels()
            val db = dbBuilder.buildBd(this)

            puntosglobales=db.scoresDao().getglobalesscore()

            viewAdapter=globalesAdapter(puntosglobales,this)
            recyclerView=findViewById<RecyclerView>(R.id.puntuaciones ).apply {
                setHasFixedSize(true)

                layoutManager = LinearLayoutManager(this@Puntuaciones)
                adapter=viewAdapter

            }

    }

    override fun onUserClicked(position: Int) {
        val intent=Intent(this,DetailUser::class.java)
        intent.putExtra("id",puntosglobales[position].userid)
        intent.putExtra("name",puntosglobales[position].name)
        startActivity(intent)
    }

}