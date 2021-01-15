package com.fiuady.quizappplus

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.nav_header.view.*

class ChangeUser : AppCompatActivity() {

    private lateinit var searchview: SearchView
    private lateinit var Listusers: ListView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_user)
        val dbBuilder: DbBuilder by viewModels()
        val db = dbBuilder.buildBd(this)

        searchview = findViewById(R.id.Searchview)
        Listusers = findViewById(R.id.Listusers)


        val usuarios = db.usersDao().getUsers()
        val nombres = ArrayList<String>()
        for (User in usuarios) {
            nombres.add(User.name)
        }
        val adapter: ArrayAdapter<String> =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, nombres)

        Listusers.adapter = adapter

        Listusers.setOnItemClickListener { parent, view, position, id ->

            val uposition = nombres.get(position)
            val usuario_actual = db.usersDao().getActiveUser()
            usuario_actual.active=0
            db.usersDao().updateUser(usuario_actual)
            val new_user=db.usersDao().getNewUser(uposition)
            new_user.active=1
            db.usersDao().updateUser(new_user)
            val intent = Intent(this@ChangeUser, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        searchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                searchview.clearFocus()
                if (nombres.contains(p0)) {
                    adapter.filter.filter(p0)
                } else {
                    Toast.makeText(applicationContext, "User not found", Toast.LENGTH_SHORT).show()
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