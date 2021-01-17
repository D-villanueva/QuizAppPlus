package com.fiuady.quizappplus

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.MotionEvent
import android.view.MotionEvent.ACTION_MOVE
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.drawerlayout.widget.DrawerLayout.LOCK_MODE_LOCKED_CLOSED
import com.facebook.stetho.Stetho
import com.fiuady.quizappplus.db.AppDatabase
import com.fiuady.quizappplus.db.Questions
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
    private lateinit var drawer:DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //inicializa la base de datos y el stheto
        val dbBuilder: DbBuilder by viewModels()
        Stetho.initializeWithDefaults(this);
        val db = dbBuilder.buildBd(this)
        val usuario_activo = db.usersDao().getActiveUser()
        var questionstoint = arrayListOf<Int>()
        jugar_button = findViewById(R.id.jugar_button)
        opciones_button = findViewById(R.id.opciones_button)
        puntuaciones_button = findViewById(R.id.puntaje_button)
        drawer = findViewById(R.id.drawer_layout)

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

        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.agregar -> dialogoUser(db)
                R.id.Editar -> EditUser(db)
                R.id.cambiar_usuarios -> changeUser()
                R.id.delete -> DeleteUser()
            }
            drawer_layout.closeDrawers()
            true
        }
        if (db.usersDao().getNumber() == 0) {
            dialogoUser(db)
        }

        TextInicio = findViewById(R.id.titulo)
        TextInicio.typeface = Typeface.createFromAsset(assets, "fonts/Balamoa.ttf")

        jugar_button.setOnClickListener { _ ->
            val questionmemory = db.questionmemoryDao().getpending(usuario_activo.id)

            if(questionmemory.finish==0)
            {
                val topicsarray = db.settingsDao().getTopicsarray(usuario_activo.id).split(" ").map { it.toInt() }
                val intopic = topicsarray.toTypedArray()
                val questions = db.questionsDao().getQuestions(intopic)
                questions.forEach{questionstoint.add(it.id)}
                questionmemory.questionAry=questionstoint.toString()
                db.questionmemoryDao().updatequestionmemory(questionmemory)
            }else if(questionmemory.finish==1) Continuegame(db)

//          var questionsAl:ArrayList<Questions>?=null
//            questionsAl = questions.toCollection(ArrayList())
            val game = Intent(this, game::class.java)
//            intent.putExtra("preguntas", questionsAl)
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
            val dbBuilder: DbBuilder by viewModels()
            val db = dbBuilder.buildBd(this)
            val usuario_activo = db.usersDao().getActiveUser()
            val header: View = navView.getHeaderView(0)
            var usuario: TextView = header.findViewById(R.id.user_activenow)
            usuario.text= usuario_activo.name
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun changeUser(){
        val changeUser = Intent(this@MainActivity, ChangeUser::class.java)
        startActivity(changeUser)
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
            db.usersDao().InsertUser(usuario_text.text.toString(), 0)
         var new_useradd=db.usersDao().getNewUser(usuario_text.text.toString())
            db.settingsDao().insertbyid(new_useradd.id)
            db.questionmemoryDao().insertbyid(new_useradd.id)
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

    private fun DeleteUser() {
        val option=1
        val deleteuser = AlertDialog.Builder(this)
        deleteuser.setTitle("Delete user")
        deleteuser.setMessage("Are you shure?")
        deleteuser.setPositiveButton("DELETE") { _, id ->
            val changeUser = Intent(this@MainActivity, ChangeUser::class.java)
            changeUser.putExtra("option", option)
            startActivity(changeUser)
        }
            .setNegativeButton("CANCEL") { dialog, id -> dialog.cancel() }
        deleteuser.setIcon(R.drawable.face_global)
        deleteuser.create()
        deleteuser.show()

    }
    private fun Continuegame(db: AppDatabase){
        val continuegame = AlertDialog.Builder(this)
        continuegame.setTitle("Continue Game")
        continuegame.setMessage("Deseas continuar con tu partida guardada?")
        continuegame.setPositiveButton("OK") { _, id ->
            val game = Intent(this, game::class.java)
            startActivity(game)
        }
            .setNegativeButton("NO") { dialog, id -> dialog.cancel()
                val usuario_activo=db.usersDao().getActiveUser()
                db.questionmemoryDao().deletejuego(usuario_activo.id)

            }
        continuegame.setIcon(R.drawable.face_global)
        continuegame.create()
        continuegame.show()
    }

}


