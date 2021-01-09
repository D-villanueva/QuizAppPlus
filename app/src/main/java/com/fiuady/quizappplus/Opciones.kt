package com.fiuady.quizappplus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.RoomMasterTable
import androidx.sqlite.db.SupportSQLiteDatabase
import com.fiuady.quizappplus.db.AppDatabase

class Opciones : AppCompatActivity() {

   private lateinit var elige_tema:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opciones)


        val db= Room.databaseBuilder(
            applicationContext,AppDatabase::class.java,
            "quizapp.db"
        ).addCallback(object:RoomDatabase.Callback(){
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)


            }
        }).build()

    }
}