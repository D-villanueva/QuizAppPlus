package com.fiuady.quizappplus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.RoomMasterTable
import androidx.sqlite.db.SupportSQLiteDatabase
import com.fiuady.quizappplus.db.AppDatabase

class Opciones : AppCompatActivity() {

    private lateinit var elige_tema: TextView
    private lateinit var todoschek: CheckBox
    private lateinit var ciencia_checkbox: CheckBox
    private lateinit var cine_checkbox: CheckBox
    private lateinit var deporte_checkbox: CheckBox
    private lateinit var musica_checkbox: CheckBox
    private lateinit var arte_checkbox: CheckBox
    private lateinit var videojuegos_checkbox: CheckBox
    private lateinit var spinner: Spinner
    private lateinit var spinnerpistas: Spinner
    var radioGroup: RadioGroup? = null
    private lateinit var switch: Switch
    private lateinit var Alto: RadioButton
    private lateinit var medio: RadioButton
    private lateinit var bajo: RadioButton

    var switchpistas=0
    var dificultad = 0
    val num_pistas = arrayOf(1, 2, 3)
    var adapterpistas: ArrayAdapter<Int>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opciones)
        val dbBuilder: DbBuilder by viewModels()
        val db = dbBuilder.buildBd(this)

        todoschek = findViewById(R.id.todos_checkbox)
        ciencia_checkbox = findViewById(R.id.ciencia_checkbox)
        cine_checkbox = findViewById(R.id.cine_checkbox)
        deporte_checkbox = findViewById(R.id.deporte_checkbox)
        musica_checkbox = findViewById(R.id.musica_checkbox)
        arte_checkbox = findViewById(R.id.arte_checkbox)
        videojuegos_checkbox = findViewById(R.id.videojuegos_checkbox)
        radioGroup = findViewById(R.id.radioGroup1)
        switch = findViewById(R.id.pistas)
        spinner = findViewById(R.id.numero_preguntas)
        spinnerpistas = findViewById(R.id.numero_pistas)
        Alto = findViewById(R.id.nivel_alto)
        medio = findViewById(R.id.nivel_medio)
        bajo = findViewById(R.id.nivel_bajo)

        val usuario_activo = db.usersDao().getActiveUser()
        val settings = db.settingsDao().getsettings(usuario_activo.id)


        adapterpistas = ArrayAdapter<Int>(this, R.layout.support_simple_spinner_dropdown_item, num_pistas)
        spinnerpistas.setAdapter(adapterpistas)

        when (settings.dificulty) {
            1 -> bajo.isChecked=true
            2 -> medio.isChecked=true
            3 -> Alto.isChecked=true
        }

        if(settings.hints==0){
            switch.isChecked=false
            spinnerpistas.isEnabled=false
        }
        else{
            switch.isChecked=true
            spinnerpistas.isEnabled=true
        }


        cine_checkbox.isChecked = settings.cine != 0
        ciencia_checkbox.isChecked = settings.ciencia != 0
        deporte_checkbox.isChecked = settings.deporte != 0
        musica_checkbox.isChecked = settings.musica != 0
        arte_checkbox.isChecked = settings.arte != 0
        videojuegos_checkbox.isChecked = settings.videojuegos != 0





        Alto.setOnClickListener {
            dificultad = 3
            db.settingsDao().senddificulty(dificultad,usuario_activo.id)
        }
        medio.setOnClickListener {
            dificultad = 2
            db.settingsDao().senddificulty(dificultad,usuario_activo.id)
        }
        bajo.setOnClickListener {
            dificultad = 1
            db.settingsDao().senddificulty(dificultad,usuario_activo.id)
        }

        switch.setOnCheckedChangeListener { _, _ ->
            if (!switch.isChecked) {
                switchpistas=0
                spinnerpistas.isEnabled = false
                db.settingsDao().sendhints(switchpistas,usuario_activo.id)
            }
            if (switch.isChecked) {
                switchpistas=1
                spinnerpistas.isEnabled = true
                db.settingsDao().sendhints(switchpistas,usuario_activo.id)

            }
        }
            spinnerpistas.onItemSelectedListener=object: AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    db.settingsDao().sendhintsquantity(num_pistas[position],usuario_activo.id)

                }
                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }


    }







}