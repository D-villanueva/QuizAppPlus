package com.fiuady.quizappplus.db

import androidx.room.*


@Entity(
    tableName="questions",
    indices=[Index(value=["question_id"],unique=true)]
)
data class Questions (
    @PrimaryKey @ColumnInfo(name="question_id") val id: Int,
    @ColumnInfo(name = "theme_id") val theme_id: Int,
    @ColumnInfo(name = "question_text") val question_text: String,


)

