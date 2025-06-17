package dev.koga.cmptutorial.games.data.repository

import dev.koga.cmptutorial.foundation.model.Resource
import dev.koga.cmptutorial.games.data.network.GamesApi
import dev.koga.cmptutorial.games.data.network.toDomain
import dev.koga.cmptutorial.games.domain.model.GameDetails
import dev.koga.cmptutorial.games.domain.model.GamePlatform
import dev.koga.cmptutorial.games.domain.model.GamesOrderBy
import dev.koga.cmptutorial.games.domain.model.SimpleGame
import dev.koga.cmptutorial.games.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GameRepositoryImpl(
    private val api: GamesApi,
) : GameRepository {

    override fun getGames(
        platform: GamePlatform?,
        orderBy: GamesOrderBy?
    ): Flow<Resource<List<SimpleGame>>> = flow {
        emit(Resource.Loading)

        try {
            val response = api.getGames()

            val games = response.map { game -> game.toDomain() }

            emit(Resource.Success(data = games))
        } catch (e: Exception) {
            emit(Resource.Error)
        }
    }

    override fun getGamesDetails(id: Int): Flow<Resource<GameDetails>> = flow {
        emit(Resource.Loading)

        try {
            val response = api.getGameDetails(id = id)

            val details = response.toDomain()

            emit(Resource.Success(data = details))
        } catch (e: Exception) {
            emit(Resource.Error)
        }
    }

// Segunda opção:
//    suspend fun getGames2(): Resource.Result<List<SimpleGame>> {
//        return try {
//            val response = gamesApi.getGames()
//
//            val games = response.map { game -> game.toDomain() }
//
//            SimpleResult.Success(data = games)
//        } catch (e: Exception) {
//            SimpleResult.Error
//        }
//    }

// Terceira opção:
//    private val _games = MutableStateFlow<Resource<List<SimpleGame>>>(
//        Resource.Loading
//    )
//    val games = _games.asStateFlow()
//
//    suspend fun loadGames() {
//        try {
//            _games.update { Resource.Loading }
//
//            val response = gamesApi.getGames()
//
//            val games = response.map { game -> game.toDomain() }
//
//            _games.update { Resource.Success(data = games) }
//        } catch (e: Exception) {
//            _games.update { Resource.Error }
//        }
//    }
}