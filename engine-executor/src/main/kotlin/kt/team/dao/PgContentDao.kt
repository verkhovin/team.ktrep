package kt.team.dao

import io.r2dbc.postgresql.PostgresqlConnectionFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import kt.team.config.Settings
import kt.team.dao.extention.parseJsonSafetely
import kt.team.entity.Content
import reactor.core.publisher.Mono
import java.util.UUID

class PgContentDao(
    private val connectionFactory: PostgresqlConnectionFactory,
    private val settings: Settings
) : ContentDao {

    @Suppress("EXPERIMENTAL_API_USAGE")
    override suspend fun findContentList(): Flow<Content> =
        Mono.from(
            connectionFactory
            .create())
            .map { conn ->
                conn.createStatement("select * from CONTENT order by CREATED desc limit $1")
                    .bind("$1", settings.contentLimitCount)
                    .execute()
                    .map {
                        it.map { row, _ ->
                            Content(
                                id = row["id"] as UUID,
                                type = row["type"] as String,
                                text = row["text"] as String,
                                tags = row.parseJsonSafetely("tags", emptyList())
                            )
                        }
                    }
                    .doFinally { conn.close().subscribe() }
            }
            .flatMapMany { it }
            .flatMap { it }
            .asFlow()
}
