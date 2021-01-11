package com.fiuady.quizappplus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SearchView
import android.widget.Toast

class ChangeUser : AppCompatActivity() {

private lateinit var searchview:SearchView
private lateinit var Listusers:ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_user)

        searchview=findViewById(R.id.Searchview)
        Listusers=findViewById(R.id.Listusers)

        val names= arrayOf("user1","user2","user1","user 4")
        val adapter:ArrayAdapter<String> = ArrayAdapter(this,android.R.layout.simple_list_item_1,names)

        Listusers.adapter=adapter
        searchview.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
               searchview.clearFocus()
                if(names.contains(p0)){
                    adapter.filter.filter(p0)
                }
                else{
                    Toast.makeText(applicationContext,"User not found",Toast.LENGTH_SHORT).show()
                }
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                adapter.filter.filter(p0)
                return false
            }

        })

    }

}