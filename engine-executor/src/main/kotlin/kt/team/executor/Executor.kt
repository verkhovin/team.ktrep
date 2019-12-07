package kt.team.executor

import kotlinx.coroutines.delay
import kt.team.dao.PgContentDao
import kt.team.dao.PgUserDao
import kt.team.enricher.PlainEnricher

suspend fun main() {
    val userDao = PgUserDao()
    val contentDao = PgContentDao()
    val enricher = PlainEnricher(userDao, contentDao)
    while (true) {
        enricher.enrich()
        delay(5000L)
    }
}