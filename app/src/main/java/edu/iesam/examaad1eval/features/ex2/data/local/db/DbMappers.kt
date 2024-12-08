package edu.iesam.examaad1eval.features.ex2.data.local.db

import com.google.gson.Gson
import edu.iesam.examaad1eval.features.ex2.domain.Game
import edu.iesam.examaad1eval.features.ex2.domain.PlayerListWrapper

fun GameEntity.toDomain(): Game = Game(
    this.id,
    this.title,
    players = Gson().fromJson(playerList, PlayerListWrapper::class.java).players
)

fun Game.toEntity(): GameEntity = GameEntity(
    this.id,
    this.title,
    playerList = Gson().toJson(PlayerListWrapper(players))
)

