package com.fiuady.quizappplus.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName="themes",
    indices=[Index(value=["title"],unique=true)]
)
data class Themes (
    @PrimaryKey @ColumnInfo (name="id") val id: Int,
    @ColumnInfo (name = "title") val title: String
)

