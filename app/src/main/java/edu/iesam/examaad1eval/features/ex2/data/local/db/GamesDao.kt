package edu.iesam.examaad1eval.features.ex2.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface GamesDao {

    @Query("SELECT * FROM $GAMES_TABLE")
    fun findAll(): List<GameEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAll(vararg games: GameEntity)
}