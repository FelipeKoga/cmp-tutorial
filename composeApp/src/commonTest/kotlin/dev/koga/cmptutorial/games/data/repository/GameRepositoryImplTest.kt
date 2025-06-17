package dev.koga.cmptutorial.games.data.repository

import app.cash.turbine.test
import dev.koga.cmptutorial.foundation.model.Resource
import dev.koga.cmptutorial.games.data.network.SimpleGameResponse
import dev.koga.cmptutorial.games.data.repository.fake.FakeGamesApi
import dev.koga.cmptutorial.games.domain.model.SimpleGame
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class GameRepositoryImplTest {

    private val games = listOf(
        SimpleGameResponse(
            id = 1,
            title = "",
            description = "",
            thumbnail = "",
            genre = "",
            platform = "",
            releaseDate = ""
        )
    )

    @Test
    fun getGames_emitsLoadingAndSuccess_whenApiCallSucceeds() {
        val api = FakeGamesApi(games = games)
        val repository = GameRepositoryImpl(api = api)

        val expectedGames = listOf(
            SimpleGame(
                id = 1,
                title = "",
                description = "",
                thumbnail = "",
                genre = "",
                platform = "",
                releaseDate = ""
            )
        )

        runTest {
            repository.getGames().test {
                assertEquals(
                    expected = Resource.Loading,
                    actual = awaitItem()
                )

                assertEquals(
                    expected = Resource.Success(data = expectedGames),
                    actual = awaitItem()
                )

                awaitComplete()
            }
        }
    }

    @Test
    fun getGames_emitsLoadingAndError_whenApiFails() {
        val api = FakeGamesApi(games = games)
        val repository = GameRepositoryImpl(api = api)

        api.shouldFailNextGamesRequest()

        runTest {
            repository.getGames().test {
                assertEquals(
                    expected = Resource.Loading,
                    actual = awaitItem()
                )

                assertEquals(
                    expected = Resource.Error,
                    actual = awaitItem()
                )

                awaitComplete()
            }
        }
    }

}