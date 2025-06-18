package dev.koga.cmptutorial.games.domain.model

data class Filter(
    val orderBy: GamesOrderBy?,
    val platform: GamePlatform?,
)