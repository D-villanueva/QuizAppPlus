package com.fiuady.quizappplus.db

import androidx.room.*


@Dao
interface SettingsDao {
    @Query("SELECT * FROM settings WHERE userid = (:id)")
    fun getsettings(id: Int): Settings

    @Update
   fun actualizar(Settings: Settings)


}