package kt.team.entity

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

class User (
    val id: UUID,
    val lastActivityTime: LocalDateTime? = null,
    val birthDate: LocalDate? = null,
    val sex: String,
    val location: String? = null,
    val products: List<String>? = null,
    val tags: List<String>? = null,
    var contents: List<UserContent> = emptyList()
)