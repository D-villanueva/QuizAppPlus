package com.fiuady.quizappplus.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName="questions_answers",
    indices=[Index(value=["question_id"],unique=true)]
    )
data class Questions_Answers(
    @PrimaryKey @ColumnInfo(name="question_id") val question_id: Int,
    @ColumnInfo(name = "answer_text") val answer_text: String,
    @ColumnInfo(name = "answer") val answer: Boolean,
)