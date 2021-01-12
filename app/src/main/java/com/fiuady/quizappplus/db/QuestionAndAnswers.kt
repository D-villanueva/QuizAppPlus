package com.fiuady.quizappplus.db

import androidx.room.Embedded
import androidx.room.Relation

data class QuestionAndAnswers (
    @Embedded
    val question: Questions,
    @Relation(
        parentColumn = "id",
        entityColumn = "question_id",
    )
    val question_id:Questions_Answers
)