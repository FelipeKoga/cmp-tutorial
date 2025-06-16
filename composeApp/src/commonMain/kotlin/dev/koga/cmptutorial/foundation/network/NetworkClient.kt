package dev.koga.cmptutorial.foundation.network

import de.jensklingenberg.ktorfit.ktorfit
import dev.koga.cmptutorial.games.data.network.createGamesApi
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


val ktorfit = ktorfit {
    baseUrl(url = "https://www.mmobomb.com/api1/")

    httpClient {
        install(ContentNegotiation) {
            json(
                Json {
                    isLenient = true
                    ignoreUnknownKeys = true
                }
            )
        }
    }
}