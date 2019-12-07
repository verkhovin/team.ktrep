package model

data class Reaction(
    val action: Action,
    val isBookmarked: Boolean
)

enum class Action(val value: String) {
    LIKE("like"),
    DISLIKE("dislike")
}
