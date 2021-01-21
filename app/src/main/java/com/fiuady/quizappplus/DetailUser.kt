package com.fiuady.quizappplus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fiuady.quizappplus.db.AppDatabase
import kotlinx.android.synthetic.main.activity_detail_user.*

class DetailUser : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var names: TextView

    var idguardado=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_user)
        val dbBuilder: DbBuilder by viewModels()
        val db = dbBuilder.buildBd(this)

        names=findViewById(R.id.name)
        val id=intent.getIntExtra("id",0)
        val name=intent.getStringExtra("name")
        names.text=name

        idguardado=id

        val resultindividual=db.scoresDao().getresultstotales(idguardado)

        viewAdapter=DetailUserAdapter(resultindividual)
        recyclerView=findViewById<RecyclerView>(R.id.puntosporuser ).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@DetailUser)
            adapter=viewAdapter
        }

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.perfilmenu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val dbBuilder: DbBuilder by viewModels()
        val db = dbBuilder.buildBd(this)

        var itemview=item.itemId
        when(itemview){
            R.id.fecha->orderbyfecha(db)
           R.id.puntospartida->orderbypuntosasc(db)
        }
        return false
    }
    fun orderbyfecha(db: AppDatabase){

        var resultindividual=db.scoresDao().getresultsbyfecha(idguardado)
        viewAdapter=DetailUserAdapter(resultindividual)
        recyclerView=findViewById<RecyclerView>(R.id.puntosporuser ).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@DetailUser)
            adapter=viewAdapter
        }
    }
    fun orderbypuntosasc(db: AppDatabase){
        var resultindividual=db.scoresDao().getresultsasc(idguardado)
        viewAdapter=DetailUserAdapter(resultindividual)
        recyclerView=findViewById<RecyclerView>(R.id.puntosporuser ).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@DetailUser)
            adapter=viewAdapter
        }
    }


}