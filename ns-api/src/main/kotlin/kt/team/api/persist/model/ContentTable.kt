package kt.team.api.persist.model

import kt.team.api.persist.customtypes.jsonb
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.datetime

object ContentTable : Table() {
    val id = uuid("id").primaryKey().autoGenerate()
    val type = varchar("type", 20)
    val created = datetime("created")
    val text = text("text")
    val imageUrl = varchar("image_url", 500)
    val videoUrl = varchar("video_url", 500)
    val articleId = uuid("article_id")
    val tags = jsonb("tags", Array<String>::class.java)
    val products = jsonb("products", Array<String>::class.java)
}