package edu.iesam.examaad1eval.features.ex2.data.local.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val GAMES_TABLE = "gamesTable"
const val GAMES_ID = "gamesId"

@Entity(tableName = GAMES_TABLE)
class GameEntity(
    @PrimaryKey @ColumnInfo(name = GAMES_ID) val id:String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "player_list") val playerList: String
)
