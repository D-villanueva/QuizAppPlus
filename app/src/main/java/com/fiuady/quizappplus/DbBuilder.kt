package com.fiuady.quizappplus

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.fiuady.quizappplus.db.AppDatabase

class DbBuilder:ViewModel() {

    fun buildBd(context: Context) :AppDatabase{
        val db = Room.databaseBuilder(
            context,
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
                    db.execSQL("INSERT INTO questions VALUES (0, 1, 'Pensamos demasiado y sentimos muy poco')")
                    db.execSQL("INSERT INTO questions VALUES (28,5,'¿Cómo se llama el enemigo de Sonic?')")

                    //settings
                    db.execSQL("INSERT INTO settings VALUES (0,1,5,1,1,1,1,1,1,1,0,1)")

                    //questions_answers
                    db.execSQL("INSERT INTO questions_answers VALUES (28,'Robotech',0)")
                    //db.execSQL("INSERT INTO questions_answers VALUES (28,'Tails',0)")
                    //db.execSQL("INSERT INTO questions_answers VALUES (28,'Dr. Robotnik',1)")
                    //db.execSQL("INSERT INTO questions_answers VALUES (28,'Dr. Malo',0)")
                }

            }).build()
        return db
    }
}