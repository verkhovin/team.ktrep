package kt.team.dao

import com.fasterxml.jackson.databind.ObjectMapper
import io.r2dbc.postgresql.PostgresqlConnectionFactory
import io.r2dbc.postgresql.codec.Json
import io.r2dbc.spi.ConnectionFactory
import kotlinx.coroutines.reactive.asFlow
import kt.team.config.Settings
import kt.team.dao.extention.parseJsonSafetely
import kt.team.entity.User
import reactor.core.publisher.Mono
import java.util.UUID

private val objectMapper = ObjectMapper()

class PgUserDao(
    private val connectionFactory: ConnectionFactory,
    private val settings: Settings
) : UserDao {

    @Suppress("EXPERIMENTAL_API_USAGE")
    override suspend fun getUsers() =
        Mono.from((connectionFactory as PostgresqlConnectionFactory)
        .create())
        .map { conn ->
            conn.createStatement("select * from NS_USER order by LAST_LOGIN desc limit $1")
                .bind("$1", settings.usersLimitCount)
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