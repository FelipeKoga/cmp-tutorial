package dev.koga.cmptutorial.games.data.network

import kotlinx.coroutines.test.runTest
import kotlin.test.Test


class GamesApiTest {

    @Test
    fun testApi() {
        runTest {
            val response = gamesApi.getGames()
            println(response)
        }
    }

    @Test
    fun testDetailsApi() {
        runTest {
            val response = gamesApi.getGameDetails(id = 452)
            println(response)
        }
    }
}