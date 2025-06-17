package dev.koga.cmptutorial.games.domain.model

enum class GamePlatform(
    private val value: String,
) {
    PC("pc"),
    BROWSER("browser"),
    ALL("all")
}