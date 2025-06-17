package dev.koga.cmptutorial.games.domain.model

enum class GamesOrderBy(
    val value: String,
) {
    RELEASE_DATE("release_date"),
    ALPHABETICAL("alphabetical"),
    RELEVANCE("relevance")
}