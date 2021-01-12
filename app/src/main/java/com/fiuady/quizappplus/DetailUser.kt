package com.fiuady.quizappplus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_detail_user.*

class DetailUser : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_user)

        val detailUser=arrayOf(
            details("05-02-20",1,"alto",3),
            details("10-02-20",2,"alto",200),
            details("03-02-20",3,"medio",5),
            details("02-02-20",3,"alto",3),
            details("01-07-20",2,"bajo",3),
            details("15-05-20",1,"alto",100),
            details("15-07-20",1,"alto",550),
            details("03-03-20",2,"alto",3),
            details("18-10-20",3,"alto",200),

            )

        viewAdapter=DetailUserAdapter(detailUser)
        recyclerView=findViewById<RecyclerView>(R.id.puntosporuser ).apply {
            setHasFixedSize(true)

            layoutManager = LinearLayoutManager(this@DetailUser)
            adapter=viewAdapter

        }

        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener{
            Toast.makeText(this,"Escorar por fecha",Toast.LENGTH_SHORT).show()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.perfilmenu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var itemview=item.itemId
        when(itemview){
            R.id.puntospartida->Toast.makeText(applicationContext,"escorar por puntos",Toast.LENGTH_SHORT).show()
        }
        return false
    }
}