package persist.customtypes

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.joda.JodaModule
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ColumnType
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.statements.api.PreparedStatementApi
import org.postgresql.util.PGobject

fun <T : Any> Table.jsonb(name: String, klass: Class<T>): Column<T> = registerColumn(name, Json(klass))


private class Json<out T : Any>(private val klass: Class<T>) : ColumnType() {

    val jsonMapper: ObjectMapper = ObjectMapper().registerModule(JodaModule())

    override fun sqlType() = "jsonb"

    override fun setParameter(stmt: PreparedStatementApi, index: Int, value: Any?) {
        val obj = PGobject()
        obj.type = "jsonb"
        obj.value = value as String
        stmt[index] = obj
    }

    override fun valueFromDB(value: Any): Any {
        if (value !is PGobject) return value
        return try {
            jsonMapper.readValue(value.value, klass)
        } catch (e: Exception) {
            e.printStackTrace()
            throw RuntimeException("Can't parse JSON: $value")
        }
    }

    override fun notNullValueToDB(value: Any): Any = jsonMapper.writeValueAsString(value)
    override fun nonNullValueToString(value: Any): String = "'${jsonMapper.writeValueAsString(value)}'"
}
