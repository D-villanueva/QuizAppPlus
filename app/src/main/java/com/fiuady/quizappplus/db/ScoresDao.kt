package com.fiuady.quizappplus.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ScoresDao {
    @Query("INSERT INTO scores (fecha,user_id,user_name,dificultad, preguntas,correctas,cheats,ppartida) VALUES (:fecha,:id,:name,:dificulty,:preguntas,:correctas,:cheats,:ppartida)")
    fun InsertScoreManual(fecha: String, id: Int, name: String, dificulty: Int, preguntas: Int, correctas: Int, cheats: Int, ppartida: Double
    )

     @Query("SELECT * FROM scores WHERE (:userid) ORDER BY partida_id DESC LIMIT 1")
     fun getresults(userid: Int):Scores

     @Query("SELECT partida_id, user_name, cheats, ppartida FROM scores ORDER BY cheats LIMIT 5")
     fun getpuntosaltos():Array<Scores>

     @Query("SELECT partida_id,user_id AS userid, user_name AS name,cheats,ppartida, SUM(ppartida) AS suma FROM scores GROUP BY user_id ORDER BY ppartida DESC")
     fun getglobalesscore():Array<SumPojo>

    @Insert
    fun insertscore(scores: Scores)

    @Update
    fun updateScore(scores: Scores)


}