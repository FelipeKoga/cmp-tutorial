package dev.koga.cmptutorial.games.data.repository.fake

import dev.koga.cmptutorial.games.data.network.GameDetailsResponse
import dev.koga.cmptutorial.games.data.network.GamesApi
import dev.koga.cmptutorial.games.data.network.SimpleGameResponse
import io.ktor.serialization.JsonConvertException
import kotlinx.coroutines.delay


class FakeGamesApi(
    private val games: List<SimpleGameResponse> = emptyList(),
    private val gameDetailsResponse: GameDetailsResponse? = null,
) : GamesApi {

    private var failNextGamesRequest = false
    private var failNextGameDetailsRequest = false

    override suspend fun getGames(): List<SimpleGameResponse> {
        delay(1000)

        if (failNextGamesRequest) {
            failNextGamesRequest = false
            throw Exception()
        }

        return games
    }

    override suspend fun getGameDetails(id: Int): GameDetailsResponse {
        delay(1000)

        if (failNextGameDetailsRequest) {
            failNextGameDetailsRequest = false
            throw JsonConvertException(message = "")
        }

        return gameDetailsResponse!!
    }

    fun shouldFailNextGamesRequest() {
        failNextGamesRequest = true
    }

    fun shouldFailNextGameDetailsRequest() {
        failNextGameDetailsRequest = true
    }
}