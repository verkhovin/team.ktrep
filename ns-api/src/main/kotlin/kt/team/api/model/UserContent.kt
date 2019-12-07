package kt.team.api.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.UUID

data class UserContent(
    @JsonProperty("content_id")
    val contentId: UUID,
    @JsonProperty("reaction")
    val reaction: String,
    @JsonProperty("isBookmarked")
    val isBookmarked: Boolean,
    @JsonProperty("isWatched")
    val isWatched: Boolean,
    @JsonProperty("score")
    val score: Double
)