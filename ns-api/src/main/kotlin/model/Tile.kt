package model

data class Tile(
    val id: String,
    val type: String,
    val score: Double,
    val isWatched: Boolean,
    val body: Body
) {
    data class Body(
        val text: String,
        val image: String,
        val videoUrl: String
    )
}
