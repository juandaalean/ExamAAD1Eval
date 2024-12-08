package edu.iesam.examaad1eval.features.ex2.data.local.db

import edu.iesam.examaad1eval.features.ex2.domain.Game

class GamesDbLocalDataSource(private val gamesDao: GamesDao) {

    fun findAllGames(): List<Game>{
        return gamesDao.findAll().map { it.toDomain() }
    }

    fun saveAllGames(games: List<Game>){
        val gamesList = games.map { game ->
            game.toEntity()
        }
        gamesDao.saveAll(*gamesList.toTypedArray())
    }
}