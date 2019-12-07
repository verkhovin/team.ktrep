package kt.team.entity

import java.time.LocalDate
import java.time.LocalDateTime

class User (
    val id: String,
    val lastActivityTime: LocalDateTime? = null,
    val birthDate: LocalDate? = null,
    val sex: String,
    val location: String? = null,
    val products: Set<String>? = null,
    val tags: Set<String>? = null,
    var contents: List<UserContent>
)