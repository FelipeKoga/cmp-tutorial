package dev.koga.cmptutorial.games.domain.model

data class GameDetails(
    val id: Int,
    val title: String,
    val description: String,
    val status: String,
    val thumbnail: String,
    val gameUrl: String,
    val genre: String,
    val platform: String,
    val publisher: String,
    val developer: String,
    val releaseDate: String,
    val profileUrl: String,
    val minimumSystemRequirements: MinimumSystemRequirements,
    val screenshots: List<Screenshot>
) {
    data class MinimumSystemRequirements(
        val os: String,
        val processor: String,
        val memory: String,
        val graphics: String,
        val storage: String,
    )

    data class Screenshot(
        val id: Int,
        val image: String,
    )
}