package com.fiuady.quizappplus.db

import androidx.room.Dao
import androidx.room.Query

@Dao
interface QuestionsDao {
    @Query("SELECT * FROM questions WHERE theme_id IN (:topicsArray)")
    fun getQuestions(topicsArray:Array<Int>):Array<Questions>
}