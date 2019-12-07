package model

import org.joda.time.DateTime

data class Article(
    val id: String,
    val created: DateTime,
    val content: String
)
