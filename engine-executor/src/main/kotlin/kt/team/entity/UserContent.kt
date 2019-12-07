package kt.team.entity

import java.util.UUID

data class UserContent(
    val contentId: UUID,
    val reaction: String? = null,
    val isBookmarked: Boolean? = null,
    val isWatched: Boolean? = null,
    val score: Double
)