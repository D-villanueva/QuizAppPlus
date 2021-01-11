package com.fiuady.quizappplus

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.facebook.stetho.Stetho
import com.fiuady.quizappplus.db.AppDatabase
import com.fiuady.quizappplus.db.User
import kotlinx.android.synthetic.main.activity_final_score.view.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_puntuaciones.*
import kotlinx.android.synthetic.main.agregar_usuarios.*
import kotlinx.android.synthetic.main.agregar_usuarios.view.*
import kotlinx.android.synthetic.main.nav_header.view.*
import kotlinx.android.synthetic.main.puntuaciones_globales.*

class MainActivity : AppCompatActivity() {

    private lateinit var TextInicio: TextView
    lateinit var toggle: ActionBarDrawerToggle
    private lateinit var jugar_button: Button
    private lateinit var opciones_button: Button
    private lateinit var puntuaciones_button: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //inicializa la base de datos y el stheto

        Stetho.initializeWithDefaults(this);
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "quizapp.db"
        ).allowMainThreadQueries()
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    db.execSQL("INSERT INTO users(id, nombre, activo) VALUES (0, 'Diego', 0)")
                    db.execSQL("INSERT INTO users(id, nombre, activo) VALUES (1, 'David', 1)")
                    //temas
                    db.execSQL("INSERT INTO themes (id, title) VALUES (0, 'Cine')");
                    db.execSQL("INSERT INTO themes (id, title) VALUES (1, 'Musica')");
                    db.execSQL("INSERT INTO themes (id, title) VALUES (2, 'Ciencia')");
                    db.execSQL("INSERT INTO themes (id, title) VALUES (3, 'Deportes')");
                    db.execSQL("INSERT INTO themes (id, title) VALUES (4, 'Arte')");
                    db.execSQL("INSERT INTO themes (id, title) VALUES (5, 'Videojuegos')");
                    //preguntas
                    db.execSQL("INSERT INTO Questions VALUES (0, 1, 'Pensamos demasiado y sentimos muy poco')")

                }

            }).build()

        jugar_button = findViewById(R.id.jugar_button)
        opciones_button = findViewById(R.id.opciones_button)
        puntuaciones_button = findViewById(R.id.puntaje_button)

        val usuario_activo = db.usersDao().getActiveUser()

        toggle = ActionBarDrawerToggle(
            this@MainActivity,
            drawer_layout,
            R.string.open,
            R.string.close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val header: View = navView.getHeaderView(0)
        var usuario: TextView = header.findViewById(R.id.user_activenow)
        usuario.text= usuario_activo.name
        usuario_activo.name
        navView.setNavigationItemSelectedListener {


            when (it.itemId) {
                R.id.agregar -> dialogoUser(db)
                R.id.Editar -> EditUser(db)
               R.id.cambiar_usuarios->changeUser()

            }
            true
        }

        if (db.usersDao().getNumber() == 0) {
            dialogoUser(db)
        }

        TextInicio = findViewById(R.id.titulo)
        TextInicio.typeface = Typeface.createFromAsset(assets, "fonts/Balamoa.ttf")

        jugar_button.setOnClickListener { _ ->
            val game = Intent(this, game::class.java)
            startActivity(game)
        }

        opciones_button.setOnClickListener { _ ->
            val option = Intent(this@MainActivity, Opciones::class.java)
            startActivity(option)

        }
        puntuaciones_button.setOnClickListener { _ ->
            val puntuaciones = Intent(this@MainActivity, Puntuaciones::class.java)
            startActivity(puntuaciones)

        }

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun changeUser(){
        val option = Intent(this@MainActivity, ChangeUser::class.java)
        startActivity(option)
    }

    private fun dialogoUser(db: AppDatabase) {
        val builder = AlertDialog.Builder(this)
        val inflater = LayoutInflater.from(this@MainActivity).context.getSystemService(
            LAYOUT_INFLATER_SERVICE
        ) as LayoutInflater

        val medio = inflater.inflate(R.layout.agregar_usuarios, null)

        builder.setTitle("Nuevo Usuario")
        builder.setView(medio).setPositiveButton("Ok") { dialog, id -> dialog.cancel() }
            .setNegativeButton("cancel") { dialog, id -> dialog.cancel() }
        builder.setIcon(R.drawable.face_global)
        var usuario_text: EditText = medio.findViewById(R.id.username)
        builder.setPositiveButton("OK") { _, id ->
            val usuario = User(db.usersDao().getNumber(), usuario_text.text.toString(), 0)
            db.usersDao().insertUser(usuario)
        }
        builder.create()
        builder.show()

    }

    private fun EditUser(db: AppDatabase) {
        val edituser = AlertDialog.Builder(this)
        val inflater = LayoutInflater.from(this@MainActivity).context.getSystemService(
            LAYOUT_INFLATER_SERVICE
        ) as LayoutInflater
        val usuario_activo = db.usersDao().getActiveUser()
        val editview = inflater.inflate(R.layout.agregar_usuarios, null)
        edituser.setTitle("Editar usuario")
        edituser.setView(editview).setPositiveButton("Ok") { dialog, id -> dialog.cancel() }
            .setNegativeButton("cancel") { dialog, id -> dialog.cancel() }
        edituser.setIcon(R.drawable.face_global)
        var usuario_text: EditText = editview.findViewById(R.id.username)
        edituser.setPositiveButton("OK") { _, id ->
            val usuario = User(usuario_activo.id, usuario_text.text.toString(), 1)
            db.usersDao().updateUser(usuario)
        }
        edituser.create()
        edituser.show()

    }

}