package kt.team.entity

import java.util.UUID

data class UserContent(
    val contentId: UUID,
    val reaction: String = "",
    val isBookmarked: Boolean = false,
    val isWatched: Boolean = false,
    val score: Double
)