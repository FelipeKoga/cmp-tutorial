package dev.koga.cmptutorial

import kotlinx.serialization.Serializable

sealed interface Routes {

    @Serializable
    data object Games : Routes


    @Serializable
    data class GameDetails(val id: Int): Routes
}

