package dev.koga.cmptutorial.games.data.network

import dev.koga.cmptutorial.games.domain.model.GameDetails
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameDetailsResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String,
    @SerialName("status")
    val status: String,
    @SerialName("thumbnail")
    val thumbnail: String,
    @SerialName("game_url")
    val gameUrl: String,
    @SerialName("genre")
    val genre: String,
    @SerialName("platform")
    val platform: String,
    @SerialName("publisher")
    val publisher: String,
    @SerialName("developer")
    val developer: String,
    @SerialName("release_date")
    val releaseDate: String,
    @SerialName("profile_url")
    val profileUrl: String,
    @SerialName("minimum_system_requirements")
    val minimumSystemRequirements: MinimumSystemRequirements,
    @SerialName("screenshots")
    val screenshots: List<Screenshot>
) {

    @Serializable
    data class MinimumSystemRequirements(
        @SerialName("os")
        val os: String,
        @SerialName("processor")
        val processor: String,
        @SerialName("memory")
        val memory: String,
        @SerialName("graphics")
        val graphics: String,
        @SerialName("storage")
        val storage: String,
    )

    @Serializable
    data class Screenshot(
        @SerialName("id")
        val id: Int,
        @SerialName("image")
        val image: String,
    )
}


fun GameDetailsResponse.toDomain() = GameDetails(
    id = id,
    title = title,
    description = description,
    status = status,
    thumbnail = thumbnail,
    gameUrl = gameUrl,
    genre = genre,
    platform = platform,
    publisher = publisher,
    developer = developer,
    releaseDate = releaseDate,
    profileUrl = profileUrl,
    minimumSystemRequirements = GameDetails.MinimumSystemRequirements(
        os = minimumSystemRequirements.os,
        processor = minimumSystemRequirements.processor,
        memory = minimumSystemRequirements.memory,
        graphics = minimumSystemRequirements.graphics,
        storage = minimumSystemRequirements.storage
    ),
    screenshots = screenshots.map { screenshot ->
        GameDetails.Screenshot(
            id = screenshot.id,
            image = screenshot.image
        )
    }
)