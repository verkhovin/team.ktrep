package kt.team.api.persist.service

import kt.team.api.model.Article
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import kt.team.api.persist.model.ArticleTable
import java.util.UUID

class ArticleService {

    fun getArticle(id: UUID) = transaction {
        val result = ArticleTable.select {
            (ArticleTable.id eq id)
        }.mapNotNull { toArticle(it) }
            .singleOrNull()
            ?: throw RuntimeException("Article with id = $id not found!")
        commit()
        result
    }

    fun save(article: Article) {}

    private fun toArticle(row: ResultRow) = Article(
        id = row[ArticleTable.id].toString(),
        created = row[ArticleTable.created],
        content = row[ArticleTable.content]
    )
}