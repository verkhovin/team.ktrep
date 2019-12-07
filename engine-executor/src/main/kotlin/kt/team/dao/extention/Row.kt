package kt.team.dao.extention

import com.fasterxml.jackson.databind.ObjectMapper
import io.r2dbc.spi.Row

val mapper = ObjectMapper()

inline fun <reified T> Row.parseJsonSafetely(name: String): T? {
    val strValue = this.get(name, String::class.java)
    return if (strValue == null) null else mapper.readValue(strValue, T::class.java)
}

inline fun <reified T> Row.parseJsonSafetely(name: String, default: T) =
    parseJsonSafetely(name) ?: default