package model

import java.time.LocalDateTime

data class Item(
    val id: String,
    val type: String,
    val score: Double,
    val created: LocalDateTime,
    val body: Body
) {
    data class Body(
        val reaction: Reaction,
        val text: String,
        val image: String,
        val videoUrl: String,
        val articleId: String
    )
}
