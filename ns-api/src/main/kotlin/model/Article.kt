package model

import java.time.LocalDateTime

data class Article(
    val id: String,
    val reaction: Reaction,
    val created: LocalDateTime,
    val content: String
)
