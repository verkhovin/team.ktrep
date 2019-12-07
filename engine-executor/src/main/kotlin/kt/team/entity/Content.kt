package kt.team.entity

import java.time.LocalDateTime

class Content (
    val id: String,
    val type: String,
    val created: LocalDateTime? = null,
    val text: String,
    val imageUrl: String? = null,
    val videoUrl: String? = null,
    val articleId: String? = null
)