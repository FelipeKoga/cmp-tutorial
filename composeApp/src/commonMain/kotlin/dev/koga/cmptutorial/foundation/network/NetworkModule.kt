package dev.koga.cmptutorial.foundation.network

import de.jensklingenberg.ktorfit.Ktorfit
import de.jensklingenberg.ktorfit.ktorfit
import dev.koga.cmptutorial.games.data.network.GamesApi
import dev.koga.cmptutorial.games.data.network.createGamesApi
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val networkModule = module {
    single<Ktorfit> {
        ktorfit {
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
    }

    single<GamesApi> {
        val ktorfit: Ktorfit = get()
        ktorfit.createGamesApi()
    }
}