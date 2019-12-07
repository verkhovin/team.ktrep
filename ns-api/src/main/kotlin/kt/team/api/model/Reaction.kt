package kt.team.api.model

data class Reaction(
    val action: String,
    val isBookmarked: Boolean
)

enum class Action(val value: String) {
    LIKE("like"),
    DISLIKE("dislike")
}
