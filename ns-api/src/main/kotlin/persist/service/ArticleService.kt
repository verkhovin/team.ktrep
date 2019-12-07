package persist.service

import model.Article
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.UUID
import persist.model.Article as ArticleEntity

class ArticleService {
    suspend fun getAllArticles(): List<Article> = transaction {
       val result =  ArticleEntity.selectAll().map { toArticle(it) }
        commit()
        result
    }

    suspend fun getArticle(id: UUID) {}
    suspend fun save(article: Article) {}
    suspend fun update(article: Article) {}

    private fun toArticle(row: ResultRow): Article =
        Article(
            id = row[ArticleEntity.id].toString(),
            created = row[ArticleEntity.created],
            content = row[ArticleEntity.content]
        )
}