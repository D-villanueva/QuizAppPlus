package com.fiuady.quizappplus.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "scores")

data class Scores (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "partida_id") val id :Int,
    @ColumnInfo(name = "fecha", ) var fecha :String?,
    @ColumnInfo(name = "user_id") val user_id : Int?,
    @ColumnInfo(name="user_name") val user_name:String?,
    @ColumnInfo(name = "preguntas",defaultValue = "0") var questionT : Int?,
    @ColumnInfo(name = "correctas",defaultValue = "0") var correct : Int?,
    @ColumnInfo(name = "cheats", defaultValue = "0") var finish : Int,
    @ColumnInfo(name = "ppartida", defaultValue = "0") var ppartida:Double
)