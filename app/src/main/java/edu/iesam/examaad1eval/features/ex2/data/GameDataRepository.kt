package edu.iesam.examaad1eval.features.ex2.data

import edu.iesam.examaad1eval.features.ex2.data.local.db.GamesDbLocalDataSource
import edu.iesam.examaad1eval.features.ex2.data.remote.MockEx2RemoteDataSource
import edu.iesam.examaad1eval.features.ex2.domain.Ex2Repository
import edu.iesam.examaad1eval.features.ex2.domain.Game

class GameDataRepository(
    private val dbLocal: GamesDbLocalDataSource,
    private val remote: MockEx2RemoteDataSource
): Ex2Repository {

    override fun getGames(): List<Game> {
        val gamesLocal = dbLocal.findAllGames()
        if (gamesLocal.isEmpty()){
            val remoteGames = remote.getGames()
            dbLocal.saveAllGames(remoteGames)
            return remoteGames
        }
        return gamesLocal
    }
}