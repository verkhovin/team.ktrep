package persist.model

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.datetime

object Article : Table() {
    val id = uuid("id").primaryKey().autoGenerate()
    val created = datetime("created")
    val content = text("content")
}