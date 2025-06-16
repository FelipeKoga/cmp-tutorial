package dev.koga.cmptutorial.games.data.network

import dev.koga.cmptutorial.games.domain.model.SimpleGame
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SimpleGameResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("short_description")
    val description: String,
    @SerialName("thumbnail")
    val thumbnail: String,
    @SerialName("genre")
    val genre: String,
    @SerialName("platform")
    val platform: String,
    @SerialName("release_date")
    val releaseDate: String?,
)

fun SimpleGameResponse.toDomain(): SimpleGame {
    return SimpleGame(
        id = id,
        title = title,
        description = description,
        thumbnail = thumbnail,
        genre = genre,
        platform = platform,
        releaseDate = releaseDate
    )
}