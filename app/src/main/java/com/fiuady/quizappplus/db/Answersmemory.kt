package com.fiuady.quizappplus.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "answermemory")
data class Answersmemory (
    @PrimaryKey(autoGenerate = true) val id:Int,
    var user_id:Int,
    var pregunta_id:Int?,
    var answerstring:String?,
    @ColumnInfo(name = "estado", defaultValue = "0") var status: Int,
    @ColumnInfo(name = "respuesta", defaultValue = "0") var resp : Int,
    @ColumnInfo(name = "cheats", defaultValue = "0") var cheats : Int,
)