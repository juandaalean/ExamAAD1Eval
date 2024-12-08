package edu.iesam.examaad1eval.app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import edu.iesam.examaad1eval.app.data.local.converters.GameConverters
import edu.iesam.examaad1eval.features.ex2.data.local.db.GameEntity
import edu.iesam.examaad1eval.features.ex2.data.local.db.GamesDao

@Database(entities = [GameEntity::class], version = 1, exportSchema = false)
@TypeConverters(GameConverters::class)
abstract class Ex2DataBase: RoomDatabase() {

    abstract fun gamesDao(): GamesDao
}