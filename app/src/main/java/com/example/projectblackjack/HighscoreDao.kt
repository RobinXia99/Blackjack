package com.example.projectblackjack

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HighscoreDao {

    @Insert
    fun insert (score: Highscore)

    @Query("SELECT * FROM highscore_table")
    fun getAll () : List<Highscore>

}