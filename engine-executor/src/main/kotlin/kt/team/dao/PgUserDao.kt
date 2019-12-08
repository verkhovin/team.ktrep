package kt.team.dao

import com.fasterxml.jackson.databind.ObjectMapper
import io.r2dbc.postgresql.PostgresqlConnectionFactory
import io.r2dbc.postgresql.codec.Json
import io.r2dbc.spi.ConnectionFactory
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactor.asFlux
import kt.team.dao.extention.parseJsonSafetely
import kt.team.entity.User
import reactor.core.publisher.Mono
import java.util.UUID

private val objectMapper = ObjectMapper()

class PgUserDao(private val connectionFactory: ConnectionFactory) : UserDao {

    @Suppress("EXPERIMENTAL_API_USAGE")
    override suspend fun getUsers() =
        Mono.from((connectionFactory as PostgresqlConnectionFactory)
        .create())
        .map { conn ->
            conn.createStatement("select * from NS_USER")
                .execute()
                .map {
                    it.map { row, _ ->
                        User(
                            id = row["id"] as UUID,
                            sex = row["gender"] as String,
                            tags = row.parseJsonSafetely("tags", emptyList())
                        )
                    }
                }
                .doFinally { conn.close().subscribe() }
        }
        .flatMapMany { it }
        .flatMap { it }
        .asFlow()


    override suspend fun updateUserContentCorr(user: User) =
        Mono.from((connectionFactory as PostgresqlConnectionFactory)
            .create())
            .map { conn ->
                conn.createStatement("update NS_USER set CONTENT = $1 where ID = $2")
                    .bind("$1", Json.of(objectMapper.writeValueAsString(user.contents)))
                    .bind("$2", user.id)
                    .execute()
                    .doFinally { conn.close().subscribe() }
            }
            .flatMapMany { it }
            .asFlow()
}