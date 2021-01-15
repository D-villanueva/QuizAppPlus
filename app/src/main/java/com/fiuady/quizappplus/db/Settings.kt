package com.fiuady.quizappplus.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "settings")
data class Settings (
    @PrimaryKey var userid : Int,
    var questionquantity:Int,
    var ciencia:Int,
    var cine:Int,
    var deporte:Int,
    var musica:Int,
    var arte:Int,
    var videojuegos:Int,
    var dificulty:Int,
    var hints:Int,
    var hintsquantity:Int
)
