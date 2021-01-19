package com.fiuady.quizappplus.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ScoresDao {
    @Query("INSERT INTO scores (fecha,user_id,user_name,preguntas,correctas,cheats,ppartida) VALUES (:fecha,:id,:name,:preguntas,:correctas,:cheats,:ppartida)")
    fun InsertScoreManual(fecha: String, id:Int,name:String,preguntas:Int,correctas:Int,cheats:Int,ppartida:Double)

    @Insert
    fun insertscore(scores: Scores)

    @Update
    fun updateScore(scores: Scores)
}