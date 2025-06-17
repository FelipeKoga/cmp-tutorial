package dev.koga.cmptutorial.games.ui

import dev.koga.cmptutorial.foundation.model.Resource
import dev.koga.cmptutorial.games.domain.model.SimpleGame

data class GamesUiState(
    val data: Resource<List<SimpleGame>>,
)

fun main() {

}
