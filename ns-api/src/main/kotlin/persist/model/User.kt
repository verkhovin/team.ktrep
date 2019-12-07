package persist.model

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.datetime
import persist.customtypes.jsonb
import java.util.UUID

object User : Table("ns_user") {
    val id = uuid("id").primaryKey().autoGenerate()
    val lastLogin = datetime("last_login")
    val birthDate = datetime("bitrh_date")
    val gender = varchar("sex", 1)
    val location = text("location")
    val products = jsonb("products", Array<String>::class.java)
    val tags = jsonb("tags", Array<KeyValue>::class.java)
    val content = jsonb("content", Content::class.java)

    data class KeyValue(
        val key: String,
        val value: String
    )

    data class Content(
        val content_id: UUID,
        val reaction: String,
        val isBookmarked: Boolean,
        val isWatched: Boolean,
        val score: Float
    )
}