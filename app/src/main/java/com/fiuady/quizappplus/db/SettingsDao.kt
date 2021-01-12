package com.fiuady.quizappplus.db

import androidx.room.Dao
import androidx.room.Query

@Dao
interface SettingsDao {
    @Query("SELECT * FROM settings WHERE userid = (:id)")
    fun getsettings(id: Int): Settings

}