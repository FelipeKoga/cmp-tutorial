package dev.koga.cmptutorial.games.data.network

import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Query
import dev.koga.cmptutorial.foundation.network.ktorfit

val gamesApi = ktorfit.createGamesApi()

interface GamesApi {

    @GET("games")
    suspend fun getGames(): List<SimpleGameResponse>

    @GET("game")
    suspend fun getGameDetails(
        @Query("id") id: Int,
    ): GameDetailsResponse

}