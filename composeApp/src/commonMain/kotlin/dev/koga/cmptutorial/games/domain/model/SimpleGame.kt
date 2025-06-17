package dev.koga.cmptutorial.games.domain.model

data class SimpleGame(
    val id: Int,
    val title: String,
    val description: String,
    val thumbnail: String,
    val genre: String,
    val platform: String,
    val releaseDate: String?,
)