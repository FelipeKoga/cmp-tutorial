package dev.koga.cmptutorial.games.domain.repository

import dev.koga.cmptutorial.foundation.model.Resource
import dev.koga.cmptutorial.foundation.model.SimpleResult
import dev.koga.cmptutorial.games.domain.model.GameDetails
import dev.koga.cmptutorial.games.domain.model.GamePlatform
import dev.koga.cmptutorial.games.domain.model.GamesOrderBy
import dev.koga.cmptutorial.games.domain.model.SimpleGame
import kotlinx.coroutines.flow.Flow

interface GameRepository {
    fun getGames(
        platform: GamePlatform?,
        orderBy: GamesOrderBy?,
    ): Flow<Resource<List<SimpleGame>>>

//    suspend fun getGames2(
//        platform: GamePlatform?,
//        orderBy: GamesOrderBy?,
//    ): SimpleResult<List<SimpleGame>>

    fun getGamesDetails(id: Int): Flow<Resource<GameDetails>>
}