package dev.koga.cmptutorial.games.data.repository

import dev.koga.cmptutorial.foundation.model.Resource
import dev.koga.cmptutorial.games.data.network.gamesApi
import dev.koga.cmptutorial.games.data.network.toDomain
import dev.koga.cmptutorial.games.domain.model.SimpleGame
import dev.koga.cmptutorial.games.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GameRepositoryImpl : GameRepository {

    override fun getGames(): Flow<Resource<List<SimpleGame>>> = flow {
        emit(Resource.Loading)

        try {
            val response = gamesApi.getGames()

            val games = response.map { game -> game.toDomain() }

            emit(Resource.Success(data = games))
        } catch (e: Exception) {
            emit(Resource.Error)
        }
    }
}