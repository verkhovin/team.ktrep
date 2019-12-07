package kt.team.dao

import io.r2dbc.postgresql.codec.Json
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactor.asFlux
import kt.team.entity.User
import reactor.core.publisher.Mono
import java.util.UUID

class PgUserDao {

     suspend fun getUsers() = Mono.from(connectionFactory
        .create())
        .map { conn ->
            conn.createStatement("select * from NS_USER")
                .execute()
                .asFlow().flatMapConcat {
                    it.map {
                         row, _ ->
                            User(
                                id = (row["id"] as UUID).toString(),
                                sex = row["gender"] as String,
                                contents = emptyList()
                            )
                    }.asFlow()
                }
                .asFlux()
                .doFinally { conn.close() }
        }
        .flatMapMany { it }
        .asFlow()


    suspend fun updateUserContentCorr(user: User) =
        Mono.from(connectionFactory
            .create())
            .map { conn ->
                conn.createStatement("update NS_USER set CONTENT = $1 where ID = $2")
                    .bind("$1", Json.of("{\"hello\": \"world\"}"))
                    .bind("$2", UUID.fromString(user.id))
                    .execute()
                    .asFlow().asFlux()
                    .doFinally { conn.close() }
            }
            .flatMapMany { it }
            .asFlow()
}