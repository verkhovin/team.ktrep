package persist.model

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.date

object Article : Table() {
    val id = uuid("id").primaryKey().autoGenerate()
    val created = date("created")
    val content = text("content")
}