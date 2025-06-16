package dev.koga.cmptutorial.games.data.network

import de.jensklingenberg.ktorfit.http.GET
import dev.koga.cmptutorial.foundation.network.ktorfit

val gamesApi = ktorfit.createGamesApi()

interface GamesApi {

    @GET("games")
    suspend fun getGames(): List<SimpleGameResponse>
}