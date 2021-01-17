package com.fiuady.quizappplus.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update

@Dao
interface QuestionmemoryDao {
    @Query("INSERT INTO questionmemory (user_id) VALUES (:userid)")
    fun insertbyid(userid:Int)

    @Query("SELECT * FROM questionmemory WHERE user_id = (:userid)")
    fun getpending(userid: Int) : Questionmemory

    @Query("DELETE FROM questionmemory WHERE :userid AND pendiente=1")
    fun deletejuego(userid:Int)

    @Update
    fun updatequestionmemory(questionmemory: Questionmemory)
}