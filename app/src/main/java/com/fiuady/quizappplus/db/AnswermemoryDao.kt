package com.fiuady.quizappplus.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface AnswermemoryDao {
    @Query("INSERT INTO answermemory (user_id, pregunta_id, answerstring) VALUES ((:user_id), (:pregunta_id), (:ansstring))")
    fun insertAnsMem(user_id:Int, pregunta_id:Int, ansstring:String)

    @Query("DELETE FROM answermemory WHERE user_id=(:userid)")
    fun  deletebyuserid(userid:Int)

    @Query("SELECT * FROM answermemory WHERE user_id=(:userid) AND pregunta_id=(:preguntaid)")
    fun getAnswersidbyQid(userid: Int, preguntaid:Int) :Answersmemory

    @Query("SELECT * FROM answermemory WHERE user_id=(:userid)")
    fun getAnswerarraybyid(userid: Int):List<Answersmemory>

    @Update
    fun updateAnsMem(memoria:Answersmemory)

}