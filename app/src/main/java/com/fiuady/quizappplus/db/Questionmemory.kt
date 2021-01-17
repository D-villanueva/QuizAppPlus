package com.fiuady.quizappplus.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "questionmemory")
data class Questionmemory (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "partida_id") val id :Int,
    @ColumnInfo(name = "fecha", ) var fecha :String?,
    @ColumnInfo(name = "user_id") val user_id : Int?,
    @ColumnInfo(name = "preguntas") var questionAry : String?,
    @ColumnInfo(name = "respuestas") var answersary : String?,
    @ColumnInfo(name = "pendiente", defaultValue = "0") var finish : Int,
    @ColumnInfo(name = "Current question", defaultValue = "0") var currentquestion:Int
)