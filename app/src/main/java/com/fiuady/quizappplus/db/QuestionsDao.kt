package com.fiuady.quizappplus.db

import androidx.room.Dao
import androidx.room.Query

@Dao
interface QuestionsDao {
    @Query("SELECT * FROM questions WHERE theme_id IN (:topicsArray) ORDER BY RANDOM() LIMIT (:questionqty)")
    fun getQuestions(topicsArray:Array<Int>, questionqty:Int):List<Questions>

    @Query("SELECT * FROM questions WHERE question_id IN (:ids) ")
    fun getQuestionsbyids(ids:Array<Int>):List<Questions>
}