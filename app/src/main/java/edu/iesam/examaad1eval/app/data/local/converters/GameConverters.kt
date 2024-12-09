package edu.iesam.examaad1eval.app.data.local.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import edu.iesam.examaad1eval.features.ex2.domain.Player
import edu.iesam.examaad1eval.features.ex2.domain.PlayerListWrapper

class GameConverters {
    private val gson = Gson()

    @TypeConverter
    fun fromPlayerList(players: List<Player>): String{
        return gson.toJson(PlayerListWrapper(players))
    }

    fun toPlayerList(playersJson: String): List<Player>{
        return gson.fromJson(playersJson, PlayerListWrapper::class.java).players
    }
}