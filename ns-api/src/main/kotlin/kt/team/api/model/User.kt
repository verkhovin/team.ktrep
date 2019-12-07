package kt.team.api.model

import org.joda.time.DateTime

data class User (
    val id: String,
    val lastLogin: DateTime,
    val birthDate: DateTime,
    val gender: String,
    val location: String,
    val product: Array<String> = emptyArray(),
    val tags: Array<KeyValue> = emptyArray(),
    val content: Array<UserContent> = emptyArray()
)