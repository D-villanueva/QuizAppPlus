package com.fiuady.quizappplus.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "users",
    indices = [Index(value = ["name"], unique = true)]
)
data class User(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") var name: String
)
