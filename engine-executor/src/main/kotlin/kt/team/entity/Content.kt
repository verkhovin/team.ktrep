package kt.team.entity

import java.time.LocalDateTime
import java.util.UUID

class Content (
    val id: UUID,
    val type: String,
    val created: LocalDateTime? = null,
    val text: String,
    val imageUrl: String? = null,
    val videoUrl: String? = null,
    val articleId: String? = null,
    val tags: List<String> = emptyList()
)