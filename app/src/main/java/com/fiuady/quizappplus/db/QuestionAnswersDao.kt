package com.fiuady.quizappplus.db

import androidx.room.Dao
import androidx.room.Query

@Dao
interface QuestionAnswersDao {
    @Query("SELECT id FROM questions_answers WHERE question_id = (:id) AND answer = 0 ORDER BY RANDOM() LIMIT (:qty)")
    fun getwrongAns(id:Int, qty:Int):List<Int>

    @Query("SELECT id FROM questions_answers WHERE question_id= (:id) AND answer = 1")
    fun getrightans(id:Int):Int

    @Query("SELECT * FROM questions_answers WHERE id in (:arryid)")
    fun getanswer(arryid:Array<Int>):List<Questions_Answers>
}