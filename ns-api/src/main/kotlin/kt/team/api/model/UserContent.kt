package kt.team.api.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.UUID

data class UserContent(
    @JsonProperty("contentId")
    val contentId: UUID,
    @JsonProperty("reaction")
    val reaction: String,
    @JsonProperty("bookmarked")
    val isBookmarked: Boolean,
    @JsonProperty("watched")
    val isWatched: Boolean,
    @JsonProperty("score")
    val score: Double
)