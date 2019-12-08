package model

import com.fasterxml.jackson.annotation.JsonFormat
import org.joda.time.DateTime

data class Article(
    val id: String,
    @JsonFormat(pattern = "yyyy-MM-dd")
    val created: DateTime,
    val content: String
)
