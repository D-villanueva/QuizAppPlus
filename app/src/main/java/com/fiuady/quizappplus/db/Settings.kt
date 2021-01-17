package com.fiuady.quizappplus.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "settings")
data class Settings (
    @PrimaryKey var userid: Int,
    @ColumnInfo (defaultValue = "5" )var questionquantity:Int,
    @ColumnInfo(defaultValue = "1")var ciencia:Int,
    @ColumnInfo(defaultValue = "0")var cine:Int,
    @ColumnInfo(defaultValue = "0")var deporte:Int,
    @ColumnInfo(defaultValue = "0")var musica:Int,
    @ColumnInfo(defaultValue = "0")var arte:Int,
    @ColumnInfo(defaultValue = "0")var videojuegos:Int,
    @ColumnInfo(defaultValue = "1")var dificulty:Int,
    @ColumnInfo(defaultValue = "0")var hints:Int,
    @ColumnInfo(defaultValue = "1")var hintsquantity:Int,
    @ColumnInfo(defaultValue = "1")var topicsarray:String
)
