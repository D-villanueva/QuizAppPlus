package com.fiuady.quizappplus.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity( tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "nombre") var name: String,
    @ColumnInfo(name= "activo") var active:Int
)

