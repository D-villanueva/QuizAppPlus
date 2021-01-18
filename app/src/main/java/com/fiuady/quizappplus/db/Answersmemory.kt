package com.fiuady.quizappplus.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "answermemory")
data class Answersmemory (
    @PrimaryKey(autoGenerate = true) val id:Int,
    var user_id:Int,
    var pregunta_id:Int?,
    var answerstring:String?
)