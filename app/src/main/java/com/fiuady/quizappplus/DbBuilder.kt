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
                    db.execSQL("INSERT INTO Questions VALUES (0, 1, 'Pensamos demasiado y sentimos muy poco')")

                    //settings
                    db.execSQL("INSERT into settings VALUES (0,1,5,1,1,1,1,1,1,1,1)")
                }

            }).build()
        return db
    }
}