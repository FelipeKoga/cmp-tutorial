package dev.koga.cmptutorial.games.domain.repository

import dev.koga.cmptutorial.foundation.model.Resource
import dev.koga.cmptutorial.games.domain.model.GameDetails
import dev.koga.cmptutorial.games.domain.model.SimpleGame
import kotlinx.coroutines.flow.Flow

interface GameRepository {
    fun getGames(): Flow<Resource<List<SimpleGame>>>
    fun getGamesDetails(id: Int): Flow<Resource<GameDetails>>
}