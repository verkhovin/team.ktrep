package kt.team.api.persist.model

import kt.team.api.model.UserContent
import kt.team.api.model.KeyValue
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.datetime
import kt.team.api.persist.customtypes.jsonb

object UserTable : Table("ns_user") {
    val id = uuid("id").primaryKey().autoGenerate()
    val lastLogin = datetime("last_login")
    val birthDate = datetime("birth_date")
    val gender = varchar("gender", 1)
    val location = text("location")
    val products = jsonb("products", Array<String>::class.java)
    val tags = jsonb("tags", Array<String>::class.java)
    val content = jsonb("content", Array<UserContent>::class.java)
}