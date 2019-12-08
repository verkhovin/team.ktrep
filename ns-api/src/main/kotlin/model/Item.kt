package model

import com.fasterxml.jackson.annotation.JsonFormat
import org.joda.time.DateTime

data class Item(
    val id: String,
    val type: String,
    val score: Double,
    @JsonFormat(pattern = "yyyy-MM-dd")
    val created: DateTime,
    val reaction: Reaction,
    val body: Body
) {
    data class Body(
        val text: String,
        val image: String,
        val videoUrl: String,
        val articleId: String
    )

    fun toTile() = Tile(
        id = id,
        type = type,
        score = score,
        isWatched = false,
        body = Tile.Body(
            text = body.text,
            image = body.image,
            videoUrl = body.videoUrl
        )
    )
}
