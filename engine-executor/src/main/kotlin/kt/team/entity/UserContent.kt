package kt.team.entity

class UserContent(
   val contentId: String,
   val reaction: String? = null,
   val isBookmarked: Boolean = false,
   val isWatched: Boolean = false,
   val score: Double

)