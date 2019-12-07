package kt.team.dao

import io.r2dbc.spi.ConnectionFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactor.asFlux
import kt.team.entity.Content
import reactor.core.publisher.Mono
import java.util.UUID

class PgContentDao(private val connectionFactory: ConnectionFactory): ContentDao {

    @Suppress("EXPERIMENTAL_API_USAGE")
    override suspend fun findContentList(): Flow<Content> =
        Mono.from(connectionFactory
            .create())
            .map { conn ->
                conn.createStatement("select * from CONTENT")
                    .execute()
                    .asFlow().flatMapConcat {
                        it.map { row, _ ->
                            Content(
                                id = (row["id"] as UUID).toString(),
                                type = row["type"] as String,
                                text = row["text"] as String
                            )
                        }.asFlow()
                    }.asFlux()
                    .doFinally { conn.close() }
            }
            .flatMapMany { it }
            .asFlow()
}