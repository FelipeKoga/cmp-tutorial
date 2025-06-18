package dev.koga.cmptutorial

import dev.koga.cmptutorial.foundation.network.networkModule
import dev.koga.cmptutorial.games.di.gamesModule
import org.koin.core.context.startKoin

object AppMultiplatform {

    fun initialize() {
        startKoin {
            modules(
                networkModule,
                gamesModule
            )
        }
    }
}