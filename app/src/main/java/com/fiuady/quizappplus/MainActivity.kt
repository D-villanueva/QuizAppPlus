package com.fiuady.quizappplus

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var TextInicio:TextView
    lateinit var toggle: ActionBarDrawerToggle
    private lateinit var jugar_button: Button
    private lateinit var opciones_button: Button
    private lateinit var puntuaciones_button:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        jugar_button = findViewById(R.id.jugar_button)
        opciones_button = findViewById(R.id.opciones_button)
        puntuaciones_button = findViewById(R.id.puntaje_button)


        toggle= ActionBarDrawerToggle(this,drawer_layout,R.string.open,R.string.close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        TextInicio=findViewById(R.id.titulo)
        TextInicio.typeface= Typeface.createFromAsset(assets,"fonts/Balamoa.ttf")

        jugar_button.setOnClickListener { _ ->
            val game = Intent(this, game::class.java)
            startActivity(game)
        }

        opciones_button.setOnClickListener { _ ->
            val option = Intent(this@MainActivity, Opciones::class.java)

            startActivity(option)

        }

    }

    override fun onOptionsItemSelected(item:MenuItem):Boolean{
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }


}