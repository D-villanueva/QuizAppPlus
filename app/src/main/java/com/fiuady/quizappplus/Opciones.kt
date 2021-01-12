package com.fiuady.quizappplus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

    var dificultad = 1

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

        when (settings.dificulty) {
            1 -> bajo.isChecked=true
            2 -> medio.isChecked=true
            3 -> Alto.isChecked=true
        }

        //if(settings.cine==0){cine_checkbox.isChecked=true}
        cine_checkbox.isChecked = settings.cine != 0
        ciencia_checkbox.isChecked = settings.ciencia != 0
        deporte_checkbox.isChecked = settings.deporte != 0
        musica_checkbox.isChecked = settings.musica != 0
        arte_checkbox.isChecked = settings.arte != 0
        videojuegos_checkbox.isChecked = settings.videojuegos != 0


        if (todoschek.isChecked) {
            ciencia_checkbox.isChecked = true
            cine_checkbox.isChecked = true
            deporte_checkbox.isChecked = true
            musica_checkbox.isChecked = true
            arte_checkbox.isChecked = true
            videojuegos_checkbox.isChecked = true
        }
        if (!switch.isChecked) {
            spinnerpistas.isEnabled = false
        }

        if (!switch.isChecked) {
            spinnerpistas.isEnabled = false
        }

        todoschek.setOnClickListener {
            if (todoschek.isChecked) {
                ciencia_checkbox.isChecked = true
                cine_checkbox.isChecked = true
                deporte_checkbox.isChecked = true
                musica_checkbox.isChecked = true
                arte_checkbox.isChecked = true
                videojuegos_checkbox.isChecked = true
            }
        }
    }
    fun selectdificulty() {
        if (Alto.isChecked) dificultad = 3
        if (medio.isChecked) dificultad = 2
        if (bajo.isChecked) dificultad = 1
    }

    override fun onBackPressed() {
        val dbBuilder: DbBuilder by viewModels()
        val db = dbBuilder.buildBd(this)


        selectdificulty()
        val usuario_activo = db.usersDao().getActiveUser()
        db.settingsDao().senddificulty(dificultad,usuario_activo.id)
        super.onBackPressed()
    }






}