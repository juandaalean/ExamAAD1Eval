package edu.iesam.examaad1eval.features.ex2.data

import edu.iesam.examaad1eval.features.ex2.data.local.db.GamesDbLocalDataSource
import edu.iesam.examaad1eval.features.ex2.data.remote.MockEx2RemoteDataSource
import edu.iesam.examaad1eval.features.ex2.domain.Ex2Repository
import edu.iesam.examaad1eval.features.ex2.domain.Game

class GameDataRepository(
    private val dbLocal: GamesDbLocalDataSource,
    private val remote: MockEx2RemoteDataSource
) : Ex2Repository {

    override fun getGames(): List<Game> {
        val cachedGames = dbLocal.findAllGames()
        val remoteGames = remote.getGames()
        val combinedGames = cachedGames.toMutableList()
        val missingGames = remoteGames.filterNot { remoteGame ->
            combinedGames.any{ cachedGame -> cachedGame.id == remoteGame.id}
        }

        combinedGames.addAll(missingGames)
        val updateCache = combinedGames.take(5)
        dbLocal.saveAllGames(updateCache)
        return combinedGames.distinctBy { it.id }.take(8)
    }
}